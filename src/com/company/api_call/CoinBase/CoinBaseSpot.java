package com.company.api_call.CoinBase;

import com.company.api_call.APICallerContract;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.AbstractCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;

/**
 * The implementation for the spot endpoint in CoinBase
 */
final public class CoinBaseSpot extends AbstractCoinBase {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The url extension that hits the spot endpoint at CoinBase
     */
    private final static String SPOT_EXT = "/spot";


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for CoinBaseSpot when a cryptocurrency and a fiat currency aren't specified (most likely when
     * the currency is not supported for the given endpoint)
     * @param controller The controller that implements the required methods
     */
    public CoinBaseSpot(final APICallerContract controller) {
        super("Spot", CoinBaseSpot.SPOT_EXT, controller);
    }
}
