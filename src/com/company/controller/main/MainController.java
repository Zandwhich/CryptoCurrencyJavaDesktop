package com.company.controller.main;

import com.company.api_call.APICallerInterface;
import com.company.api_call.CoinBase.CoinBaseBuy;
import com.company.api_call.CoinBase.CoinBaseSell;
import com.company.api_call.CoinBase.CoinBaseSpot;
import com.company.api_call.CoinCap.CoinCap;
import com.company.api_call.CryptoCompare.CryptoCompare;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.Errors;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.controller.AbstractController;
import com.company.tool.exception.currency_not_supported.AbstractCurrencyNotSupported;
import com.company.view.window.about.AboutJFrameWindow;
import com.company.view.window.error.endpoint_update_error.EndpointUpdateErrorWindow;
import com.company.view.window.error.bad_data_error.BadDataErrorWindow;
import com.company.view.window.error.default_error.DefaultErrorWindow;
import com.company.view.window.error.network_error.NetworkErrorWindow;
import com.company.view.window.error.parse_error.ParseErrorWindow;
import com.company.view.window.main.MainJFrameWindow;
import com.company.view.window.main.MainWindowInterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The main controller of the application which controls the main page
 */
final public class MainController extends AbstractController implements MainControllerInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The list of all the API endpoints
     */
    private final ArrayList<APICallerInterface> masterEndpointList = new ArrayList<>();

    /**
     * The list of the endpoints that are currently being displayed
     */
    private final ArrayList<APICallerInterface> activeEndpointList = new ArrayList<>();

    /**
     * The currently selected fiat currency
     */
    private FiatCurrencies currentFiat = FiatCurrencies.USD;

    /**
     * The currently selected cryptocurrency
     */
    private CryptoCurrencies currentCrypto = CryptoCurrencies.BTC;

    /**
     * The main window of the application
     */
    private final MainWindowInterface mainWindow = new MainJFrameWindow(this);


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the MainController
     */
    public MainController() {

        // Get the dropdown to display the default currencies
        this.mainWindow.updateDropdowns(this.currentCrypto, this.currentFiat);

        /* CoinBase */
        masterEndpointList.add(new CoinBaseBuy(this));
        masterEndpointList.add(new CoinBaseSell(this));
        masterEndpointList.add(new CoinBaseSpot(this));

        /* CoinCap */
        masterEndpointList.add(new CoinCap(this));

        /* CryptoCompare */
        masterEndpointList.add(new CryptoCompare(this));

        this.mainWindow.setEndpoints(
                masterEndpointList
                        .stream()
                        .map(APICallerInterface::getName)
                        .collect(Collectors.toList()));

        this.updateActiveEndpoints();
        this.refresh();
    }


    /* ************ *
     *    Methods   *
     * ************ */

    @Override
    public ArrayList<APICallerInterface> getMasterEndpointList() { return this.masterEndpointList; }

    /**
     * The method to be run on a near-infinite loop to run the program
     */
    public void run() {
      
        while (true) { }

    }

    @Override
    public void refresh() {
        super.checkConnection();

        // TODO: Maybe don't check the internet connection every time...
        if (!super.checkConnection()) this.errorDisplay(Errors.NETWORK_ERROR);
        else this.updatePrices();
    }

    @Override
    public void updatePrices() {
        for (final APICallerInterface endpoint : this.activeEndpointList) {
            new Thread(() -> {
                try {
                    endpoint.updatePriceAndNotify(this.currentCrypto, this.currentFiat);
                } catch (final AbstractCurrencyNotSupported e) {
                    // TODO: Remove this endpoint from the display
                    // TODO: Should we do this better? Loop through and determine first which endpoints to display? And
                    //  not catch it via an exception?
                }
            }).start();
        }
    }

    @Override
    public void errorDisplay(final Errors error, final String name, final CryptoCurrencies crypto,
                             final FiatCurrencies fiat) {

        switch (error) {
            case ENDPOINT_UPDATE_ERROR:
                new EndpointUpdateErrorWindow(this, name, crypto, fiat);
                return;
            case NETWORK_ERROR:
                new NetworkErrorWindow(this);
                return;
            case PARSE_ERROR:
                new ParseErrorWindow(this, name, crypto, fiat);
            case BAD_DATA:
                new BadDataErrorWindow(this, name);
                return;
            default:
                new DefaultErrorWindow(this);
        }
    }

    @Override
    public void errorDisplay(final Errors error) {

        switch (error) {
            case ENDPOINT_UPDATE_ERROR:
                new EndpointUpdateErrorWindow(this);
                return;
            case NETWORK_ERROR:
                new NetworkErrorWindow(this);
                return;
            case PARSE_ERROR:
                new ParseErrorWindow(this);
            case BAD_DATA:
                new BadDataErrorWindow(this, "");
                return;
            default:
                new DefaultErrorWindow(this);
        }
    }

    @Override
    public void updateFiatCurrency(final FiatCurrencies fiatCurrency) {
        this.currentFiat = fiatCurrency;
        this.updateActiveEndpoints();

        for (final APICallerInterface endpoint : this.activeEndpointList) {
            try {
                this.notifyPriceSet(endpoint, this.currentCrypto, this.currentFiat, endpoint.getPrice(this.currentCrypto, this.currentFiat), endpoint.getLastSuccessfulUpdated(this.currentCrypto, this.currentFiat) != null, endpoint.getLastSuccessfulUpdated(this.currentCrypto, this.currentFiat));
            } catch (final AbstractCurrencyNotSupported exception) {
                // TODO: Figure out when this fails (it shouldn't)
            }
        }
    }

    @Override
    public void updateCryptocurrency(final CryptoCurrencies cryptoCurrency) {
        this.currentCrypto = cryptoCurrency;
        this.updateActiveEndpoints();

        for (final APICallerInterface endpoint : this.activeEndpointList) {
            try {
                this.notifyPriceSet(endpoint, this.currentCrypto, this.currentFiat, endpoint.getPrice(this.currentCrypto, this.currentFiat), endpoint.getLastSuccessfulUpdated(this.currentCrypto, this.currentFiat) != null, endpoint.getLastSuccessfulUpdated(this.currentCrypto, this.currentFiat));
            } catch (final AbstractCurrencyNotSupported exception) {
                // TODO: Figure out when this fails (it shouldn't)
            }
        }
    }

    @Override
    public void notifyUpdating(final APICallerInterface endpoint, final CryptoCurrencies crypto,
                               final FiatCurrencies fiat, final boolean isUpdating) {
        // First, check if this is still for the current crypto/fiat combination
        if (crypto == this.currentCrypto && fiat == this.currentFiat)
            this.mainWindow.setRefreshing(endpoint.getName());
    }

    @Override
    public void notifyPriceSet(final APICallerInterface endpoint, final CryptoCurrencies crypto,
                               final FiatCurrencies fiat, final double price, final boolean isSuccessful,
                               final LocalDateTime lastUpdated) {
        // First, check if this is still for the current crypto/fiat combination
        if (crypto == this.currentCrypto && fiat == this.currentFiat)
            this.mainWindow.updatePrice(endpoint.getName(), price, isSuccessful, lastUpdated);
    }

    @Override
    public void aboutPagePopUp() {
        new AboutJFrameWindow();
    }

    private void updateActiveEndpoints() {
        this.activeEndpointList.clear();

        for (final APICallerInterface endpoint : this.masterEndpointList) {
            if (endpoint.canUseCryptoAndFiatCurrencies(this.currentCrypto, this.currentFiat)) {
                this.activeEndpointList.add(endpoint);
            }
        }
    }
}
