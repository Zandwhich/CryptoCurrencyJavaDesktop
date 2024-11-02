package com.company.api_call;

import com.company.api_call.endpoint_memory.EndpointMemory;
import com.company.api_call.endpoint_memory.EndpointMemoryInterface;
import com.company.tool.enums.Errors;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.Currency;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.BadData;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;
import json_simple.JSONObject;
import json_simple.parser.JSONParser;
import json_simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * The abstract class for API calls, which all API calls are based off of
 */
public abstract class AbstractAPICaller implements APICallerInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The name of the API endpoint
     */
    private final String name;

    /**
     * The controller that calls this API caller
     */
    private final APICallerContract controller;

    /**
     * The cryptocurrencies that this website can use
     */
    private final CryptoCurrencies[] acceptedCryptos;

    /**
     * The fiat currencies that this website can use
     */
    private final FiatCurrencies[] acceptedFiats;

    private final EndpointMemoryInterface memory;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for AbstractAPICaller
     * @param acceptedCryptos The accepted cryptocurrencies for this website
     * @param acceptedFiats The accepted fiat currencies for this website
     * @param name The name of the API endpoint
     * @param controller The controller that calls this API caller
     */
    public AbstractAPICaller(final CryptoCurrencies[] acceptedCryptos, final FiatCurrencies[] acceptedFiats,
                             final String name, final APICallerContract controller) {
        // The order here is important
        this.acceptedCryptos = acceptedCryptos;
        this.acceptedFiats = acceptedFiats;

        this.controller = controller;
        this.name = name;

        this.memory = new EndpointMemory(this.acceptedCryptos, this.acceptedFiats);
    }


    /* ************ *
     *    Methods   *
     * ************ */

    protected abstract String createURLStringForCall(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    /**
     * Gets the price from the JSON object which was returned from a call
     *
     * @param jsonObject The returned, parsed JSON object from the call
     * @return The price extracted from the JSON object.
     */
    protected abstract double extractPrice(final JSONObject jsonObject, final CryptoCurrencies crypto,
                                           final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported, BadData;

    @Override
    public boolean canUseCryptoCurrency(final CryptoCurrencies crypto) {
        return Arrays.asList(this.acceptedCryptos).contains(crypto);
    }

    @Override
    public boolean canUseFiatCurrency(final FiatCurrencies fiat) {
        return Arrays.asList(this.acceptedFiats).contains(fiat);
    }

    @Override
    public boolean canUseCryptoAndFiatCurrencies(final CryptoCurrencies cryptoCurrency, final FiatCurrencies fiatCurrency) {
        return this.canUseCryptoCurrency(cryptoCurrency) && this.canUseFiatCurrency(fiatCurrency);
    }

    /**
     * A method to avoid duplication in implementation of the "canUseCryptoCurrency" and "canUseFiatCurrency" methods
     * that are in each of the children classes. This method is to only be called by the children classes
     * @param acceptedCurrencies The array of accepted currencies for this endpoint
     * @param attemptedCurrency The currency in question to see if it is in the list of accepted currencies for this
     *                          endpoint
     * @return If the attemptedCurrency is in the list of acceptedCurrencies
     */
    protected static boolean canUseCurrency(final Currency[] acceptedCurrencies, final Currency attemptedCurrency) {
        return Arrays.asList(acceptedCurrencies).contains(attemptedCurrency);
    }

    protected void throwIfNotAcceptedCurrency(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        if (!this.canUseCryptoCurrency(crypto)) throw new CryptoCurrencyNotSupported(crypto);
        if (!this.canUseFiatCurrency(fiat)) throw new FiatCurrencyNotSupported(fiat);
    }

    @Override
    public String getName() { return this.name; }

    /* TODO: This is where you left off. When we switch from one currency to the next, but don't refresh, we aren't
     *   updating the values by getting the latest values.
     */
    @Override
    public double getPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported  {
        return this.memory.getPrice(crypto, fiat);
    }

    @Override
    public LocalDateTime getLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.memory.getLastSuccessfulUpdated(crypto, fiat);
    }

    @Override
    public boolean isUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.memory.isUpdating(crypto, fiat);
    }

    @Override
    public boolean wasLastUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        return this.memory.wasLastUpdateSuccessful(crypto, fiat);
    }

    /**
     * Updates the price and notifies the controller
     */
    @Override
    public void updatePriceAndNotify(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {

        this.setIsUpdatingAndNotify(crypto, fiat, true);
        try {
            final double newPrice = this.getNewPrice(crypto, fiat);
            this.setNewPriceAndNotify(crypto, fiat, newPrice);
        } catch (final BadData exception) {
            // TODO: The flow here is off. We're not properly updating the memory to say if it's
            //  failed the update
            this.setIsUpdatingAndNotify(crypto, fiat, false);
        }

    }

    private void setIsUpdatingAndNotify(final CryptoCurrencies crypto, final FiatCurrencies fiat,
                                        final boolean isUpdating)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {

        this.memory.setUpdating(crypto, fiat, isUpdating);
        this.controller.notifyUpdating(this, crypto, fiat, isUpdating);
    }

    private double getNewPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported, BadData {

        final JSONObject response = this.getRequestCall(crypto, fiat);
        return this.extractPrice(response, crypto, fiat);
    }

    // TODO: Update this when price errors get updated
    private void setNewPriceAndNotify(final CryptoCurrencies crypto, final FiatCurrencies fiat, final double price)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {

        this.memory.setUpdating(crypto, fiat, false);
        if (price != -1) this.memory.setPrice(crypto, fiat, price);
        if (price != -1) this.memory.setLastSuccessfulUpdated(crypto, fiat, LocalDateTime.now());
        this.memory.setWasLastUpdateSuccessful(crypto, fiat, price != -1);
        this.controller.notifyPriceSet(this, crypto, fiat, price, price != -1,
                this.memory.getLastSuccessfulUpdated(crypto, fiat));
    }

    /**
     * Hits the url and retrieves the JSON. If there is an error, it returns null
     * @return The parsed JSON object returned as a result of the call. If there is an error, then it returns null
     */
    private JSONObject getRequestCall(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        /*
        Refer to:
            https://docs.oracle.com/javase/tutorial/networking/urls/connecting.html
            https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
            https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
            https://stackoverflow.com/questions/2793150/how-to-use-java-net-urlconnection-to-fire-and-handle-http-requests
         */

        JSONObject jsonObject;
        URLConnection connection;
        BufferedReader in = null;
        try {
            // Set up the connection and get the input stream
            // TODO: Setup setConnectTimeout and steReadTimeout?
            connection = new URL(this.createURLStringForCall(crypto, fiat)).openConnection();
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final JSONParser parser = new JSONParser();

            jsonObject = (JSONObject) parser.parse(in);
        } catch (final MalformedURLException e) {
            // TODO: This really shouldn't happen
            // TODO: Should I make a distinct error for this?
            this.controller.errorDisplay(Errors.ENDPOINT_UPDATE_ERROR, this.getName(), crypto, fiat);
            e.printStackTrace();

            // TODO: Throw an error here?

            jsonObject = null;
        } catch (final IOException e) {
            // openConnection(), connection.connect(), or parser.parse(in) failed
            this.controller.errorDisplay(Errors.ENDPOINT_UPDATE_ERROR, this.getName(), crypto, fiat);
            e.printStackTrace();

            // TODO: Throw an error?

            jsonObject = null;
        } catch (final ParseException e) {
            // parser.parse(in) failed
            this.controller.errorDisplay(Errors.PARSE_ERROR, this.getName(), crypto, fiat);
            e.printStackTrace();

            // TODO: Throw an error?

            jsonObject = null;
        }
        finally {
            try {
                if (in != null) in.close();
            } catch (final IOException ignored) {}
            // TODO: Disconnect the connection here. (Do I need to cast as  HttpURLConnection/HttpsURLConnection) above
            //  when I do .openConnection();
        }

        return jsonObject;
    }
}
