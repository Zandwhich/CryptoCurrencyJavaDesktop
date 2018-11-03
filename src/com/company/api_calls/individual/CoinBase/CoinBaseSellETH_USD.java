package com.company.api_calls.individual.CoinBase;

/**
 * The class used for CoinBase Sell ETH to USD
 */
public class CoinBaseSellETH_USD extends AbstractCoinBase {
    /****************
     *    Fields    *
     ****************/

    /**
     * TODO: Fill in
     */
    public final static String URL_EXT = "ETH-USD/sell";

    /**
     * TODO: Fill in
     */
    public final static String CRYPTO_CURRENCY = "ETH";

    /**
     * TODO: Fill in
     */
    public final static String FIAT_CURRENCY = "USD";

    /**
     * TODO: Fill in
     */
    public final static String NAME = "CoinBase-Sell Ethereum/USD";

    /****************
     * Constructors *
     ****************/

    /**
     * The default constructor for CoinBase Sell ETH/USD
     */
    public CoinBaseSellETH_USD() {
        super(CoinBaseSellETH_USD.CRYPTO_CURRENCY, CoinBaseSellETH_USD.FIAT_CURRENCY, CoinBaseSellETH_USD.NAME,
                CoinBaseSellETH_USD.URL_EXT);
    }//end CoinBaseSellETH_USD()

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
    public String getUrlExt() { return CoinBaseSellETH_USD.URL_EXT; }//end getUrlExt()

}//end CoinBaseSellETH_USD
