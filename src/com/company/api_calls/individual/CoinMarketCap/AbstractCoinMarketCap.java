package com.company.api_calls.individual.CoinMarketCap;

import com.company.api_calls.AbstractJSONCaller;
import json_simple.JSONObject;

/**
 * TODO: Fill in
 */
public abstract class AbstractCoinMarketCap extends AbstractJSONCaller {

    /****************
     *    Fields    *
     ****************/

    /**
     * The base URL for CoinMarketCap requests
     */
    public final static String BASE_URL = "https://api.coinmarketcap.com/v2/ticker/";

    /****************
     * Constructors *
     ****************/

    /**
     * TODO: Fill in
     * @param cryptoCurrency
     * @param fiatCurrency
     * @param name
     * @param urlExt
     */
    public AbstractCoinMarketCap(final String cryptoCurrency, final String fiatCurrency, final String name,
                                 final String urlExt) {
        super(cryptoCurrency, fiatCurrency, name, AbstractCoinMarketCap.BASE_URL + urlExt);
    }//end AbstractCoinMarketCap()

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
    public String getBaseUrl() { return AbstractCoinMarketCap.BASE_URL; }//end getBaseUrl()

    /* Protected */

    /**
     * TODO: Fill in
     * @param jsonObject
     * @return
     */
    @Override
    protected double extractPrice(JSONObject jsonObject) {
        if (jsonObject == null) return -1;

        JSONObject data = (JSONObject) jsonObject.get("data");

        if (data == null) return -1;

        JSONObject quotes = (JSONObject) data.get("quotes");

        if (quotes == null) return -1;

        return this.extractFiat(quotes);
    }//end extractPrice()

    /**
     * TODO: Fill in
     * @param quotes
     * @return
     */
    protected abstract double extractFiat(JSONObject quotes);
}//end AbstractCoinMarketCap