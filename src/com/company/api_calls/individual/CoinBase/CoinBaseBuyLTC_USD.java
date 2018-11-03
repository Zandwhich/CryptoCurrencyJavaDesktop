package com.company.api_calls.individual.CoinBase;

/**
 * The class used for CoinBase Buy LTC to USD
 */
public class CoinBaseBuyLTC_USD extends AbstractCoinBase {

    /****************
     *    Fields    *
     ****************/

    /**
     * TODO: Fill in
     */
    public final static String URL_EXT = "LTC-USD/buy";

    /**
     * TODO: Fill in
     */
    public final static String CRYPTO_CURRENCY = "LTC";

    /**
     * TODO: Fill in
     */
    public final static String FIAT_CURRENCY = "USD";

    /**
     * TODO: Fill in
     */
    public final static String NAME = "CoinBase-Buy Litecoin/USD";

    /****************
     * Constructors *
     ****************/

    /**
     * The default constructor for CoinBase Buy LTC/USD
     */
    public CoinBaseBuyLTC_USD() {
        super(CoinBaseBuyLTC_USD.CRYPTO_CURRENCY, CoinBaseBuyLTC_USD.FIAT_CURRENCY, CoinBaseBuyLTC_USD.NAME,
                CoinBaseBuyLTC_USD.URL_EXT);
    }//end CoinBaseBuyLTC_USD()

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
    public String getUrlExt() { return CoinBaseBuyLTC_USD.URL_EXT; }//end getUrlExt()

}//end CoinBaseBuyLTC_USD
