package com.company.api_calls_deprecated.individual.CoinBase;

import com.company.controller.ControllerInterface;

/**
 * The class used for CoinBase Sell BH to USD
 */
public class CoinBaseSellBCH_USD extends AbstractCoinBase {

    /****************
     *    Fields    *
     ****************/

    /**
     * TODO: Fill in
     */
    public final static String URL_EXT = "BCH-USD/sell";

    /**
     * TODO: Fill in
     */
    public final static String CRYPTO_CURRENCY = "BCH";

    /**
     * TODO: Fill in
     */
    public final static String FIAT_CURRENCY = "USD";

    /**
     * TODO: Fill in
     */
    public final static String NAME = "CoinBase-Sell Bitcoin Cash/USD";

    /****************
     * Constructors *
     ****************/

    /**
     * The default constructor for CoinBase Sell BCH/USD
     * @param controller The controller that calls this endpoint
     */
    public CoinBaseSellBCH_USD(ControllerInterface controller) {
        super(CoinBaseSellBCH_USD.CRYPTO_CURRENCY, CoinBaseSellBCH_USD.FIAT_CURRENCY, CoinBaseSellBCH_USD.NAME,
                CoinBaseSellBCH_USD.URL_EXT, controller);
    }//end CoinBaseSellBCH_USD()

    /****************
     *   Methods    *
     ****************/

    /* Public */

    // Getters

    /**
     * Returns the url extension
     * @return The url extension
     */
    @Override
    public String getUrlExt() { return CoinBaseSellBCH_USD.URL_EXT; }//end getUrlExt()

}//end CoinBaseSellBCH_USD