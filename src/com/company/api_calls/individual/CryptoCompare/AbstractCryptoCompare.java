package com.company.api_calls.individual.CryptoCompare;

import com.company.api_calls.AbstractJSONCaller;

/**
 * TODO: Fill in
 */
public abstract class AbstractCryptoCompare extends AbstractJSONCaller {

    /****************
     *    Fields    *
     ****************/

    /**
     * The base URL for CryptoCompare requests
     * TODO: Put in actual base URL
     */
    public final static String BASE_URL = "";

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
    public AbstractCryptoCompare(final String cryptoCurrency, final String fiatCurrency, final String name,
                                 final String urlExt) {
        super(cryptoCurrency, fiatCurrency, name, AbstractCryptoCompare.BASE_URL + urlExt);
    }//end AbstractCryptoCompare()

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
    public String getBaseUrl() { return AbstractCryptoCompare.BASE_URL; }//end getBaseUrl()

    /* Protected */

    // TODO: Override the extract price method
}//end AbstractCryptoCompare
