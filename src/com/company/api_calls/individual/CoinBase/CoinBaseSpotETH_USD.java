package com.company.api_calls.individual.CoinBase;

/**
 * TODO: Fill in
 */
public class CoinBaseSpotETH_USD extends AbstractCoinBase{

    /****************
     *    Fields    *
     ****************/

    /**
     * TODO: Fill in
     */
    public final static String URL_EXT = "ETH-USD/spot";

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
    public final static String NAME = "CoinBase-Spot Ethereum/USD";

    /****************
     * Constructors *
     ****************/

    /**
     * The default constructor for CoinBase Spot ETH/USD
     */
    public CoinBaseSpotETH_USD() {
        super(CoinBaseSpotETH_USD.CRYPTO_CURRENCY, CoinBaseSpotETH_USD.FIAT_CURRENCY, CoinBaseSpotETH_USD.NAME,
                CoinBaseSpotETH_USD.URL_EXT);
    }//end CoinBaseSpotETH_USD()

    /****************
     *   Methods    *
     ****************/

    /* Public */

    // Getters

    /**
     * TODO: Fill in
     * @return
     */
    @Override
    public String getUrlExt() { return CoinBaseSpotETH_USD.URL_EXT; }//end getUrlExt()

}//end CoinBaseSpotETH_USd