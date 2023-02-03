package com.company.api_calls;

import com.company.controller.ControllerInterface;
import com.company.tools.enums.currency.CryptoCurrencies;
import com.company.tools.enums.currency.Currency;
import com.company.tools.enums.currency.FiatCurrencies;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The abstract class for API calls, which all API calls are based off of
 */
public abstract class AbstractAPICaller implements APICallerInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The last received price
     */
    private double price;

    /**
     * The crypto currency (i.e. BTC, ETH, LTC, etc.)
     */
    private CryptoCurrencies cryptoCurrency;

    /**
     * The fiat currency (i.e. USD, CAD, PLN, etc.)
     */
    private FiatCurrencies fiatCurrency;

    /**
     * If the API has a price to display
     */
    private boolean hasPrice;

    /**
     * The name of the API endpoint
     */
    private String name;

    /**
     * The url to hit
     */
    private URL url;

    /**
     * If the last attempt to update the prices ended in failure
     */
    private boolean hasFailedLastUpdate;

    /**
     * The controller that calls this API caller
     */
    private final ControllerInterface controller;

    /**
     * The cryptocurrencies that this website can use
     */
    private final CryptoCurrencies[] acceptedCryptoCurrencies;

    /**
     * The fiat currencies that this website can use
     */
    private final FiatCurrencies[] acceptedFiatCurrencies;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for AbstractAPICaller
     * @param cryptoCurrency The currency (i.e. BTC, ETH, LTC, etc.)
     * @param fiatCurrency The fiat currency to compare against (USD, CAD, PLN, etc.)
     * @param acceptedCryptoCurrencies The accepted cryptocurrencies for this website
     * @param acceptedFiatCurrencies The accepted fiat currencies for this website
     * @param name The name of the API endpoint
     * @param url The url to hit
     * @param controller The controller that calls this API caller
     */
    public AbstractAPICaller(final CryptoCurrencies cryptoCurrency, final FiatCurrencies fiatCurrency,
                             final CryptoCurrencies[] acceptedCryptoCurrencies,
                             final FiatCurrencies[] acceptedFiatCurrencies, final String name, final String url,
                             final ControllerInterface controller) {
        this.controller = controller;
        this.cryptoCurrency = cryptoCurrency;
        this.fiatCurrency = fiatCurrency;
        this.hasPrice = false;
        // There has not been a failure to update, as there hasn't been a request made yet
        this.hasFailedLastUpdate = false;
        this.price = 0.0;
        this.name = name;
        try {
            this.url = new URL(url);
        }
        catch (MalformedURLException e) {
            // Bad URL input
            e.printStackTrace();

            // Not really sure, but I feel like this should be set to true
            this.hasFailedLastUpdate = true;

            // TODO: Figure out what to do when a bad URL is inputted (this shouldn't happen as the URLs are to be hard-coded in)
            //       Throw an error?
        }
        this.acceptedCryptoCurrencies = acceptedCryptoCurrencies;
        this.acceptedFiatCurrencies = acceptedFiatCurrencies;
        
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() { return this.price; }

    /**
     * {@inheritDoc}
     */
    @Override
    public CryptoCurrencies getCryptoCurrency() { return this.cryptoCurrency; }

    /**
     * {@inheritDoc}
     */
    public FiatCurrencies getFiatCurrency() { return this.fiatCurrency; }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getHasPrice() { return this.hasPrice; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return this.name; }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getUrl() { return this.url; }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUrlString() { return this.url.toString(); }

    /**
     * Gets the Base URL of the API call
     * @return The Base URL of the API call
     */
    public abstract String getBaseUrl();

    /**
     * Gets if the last attempt at updating the price ended in failure
     * @return If the last attempt at updating the price ended in failure
     */
    public boolean getHasFailedLastUpdate() { return this.hasFailedLastUpdate; }

    /**
     * Gets the controller
     * @return The controller
     */
    public ControllerInterface getController() { return this.controller; }

    /**
     * Updates the price
     */
    private void updatePrice() {
        final double newPrice = this.getNewPrice();
        // TODO: Once we start throwing errors this will be changed
        if (newPrice != -1) {
            this.setPrice(newPrice);
            this.setHasFailedLastUpdate(false);
            this.setHasPrice(true);
        }
        else {
            this.setHasFailedLastUpdate(true);
        }
    }

    /**
     * Updates the price and notifies the controller
     */
    public void updatePriceAndNotify() {
        this.updatePrice();
        this.controller.notifyWindowOfUpdate();
    }

    /**
     * Sets the price
     * @param price The new price
     */
    protected void setPrice(final double price) { this.price = price; }

    /**
     * Sets the cryptocurrency
     * @param cryptoCurrency The cryptocurrency
     */
    protected void setCryptoCurrency(final CryptoCurrencies cryptoCurrency) { this.cryptoCurrency = cryptoCurrency; }

    /**
     * Sets the fiat currency
     * @param fiatCurrency The fiat currency
     */
    protected void setFiatCurrency(final FiatCurrencies fiatCurrency) { this.fiatCurrency = fiatCurrency; }

    /**
     * Sets the name of the API endpoint
     * @param name The name of the API endpoint
     */
    public void setName(final String name) { this.name = name; }

    /**
     * Sets the URL to hit
     * @param url The url to hit
     */
    public void setUrl(final URL url) { this.url = url; }

    /**
     * Sets if the last update failed
     * @param hasFailedLastUpdate If the last update failed
     */
    public void setHasFailedLastUpdate(final boolean hasFailedLastUpdate) { this.hasFailedLastUpdate = hasFailedLastUpdate; }

    /**
     * Sets if there is a price to display
     * @param hasPrice If there is a price to display
     */
    protected void setHasPrice(final boolean hasPrice) { this.hasPrice = hasPrice; }

    /**
     * Gets the new updated price from the API endpoint
     * @return The new updated price from the API endpoint
     */
    protected abstract double getNewPrice();

    /**
     * A method to avoid duplication in implementation of the "canUseCryptoCurrency" and "canUseFiatCurrency" methods
     * that are in each of the children classes. This method is to only be called by the children classes
     * @param acceptedCurrencies The array of accepted currencies for this endpoint
     * @param attemptedCurrency The currency in question to see if it is in the list of accepted currencies for this
     *                          endpoint
     * @return If the attemptedCurrency is in the list of acceptedCurrencies
     */
    protected static boolean canUseCurrency(final Currency[] acceptedCurrencies, final Currency attemptedCurrency) {
        for (final Currency acceptedCurrency : acceptedCurrencies) {
            if (acceptedCurrency.equals(attemptedCurrency)) return true;
        }

        return false;
    }

}
