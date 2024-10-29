package com.company.api_call.CoinBase;

import com.company.api_call.APICallerContract;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;

/**
 * The implementation of the CoinBase API that hits the 'buy' endpoint
 */
final public class CoinBaseBuy extends AbstractCoinBase {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The url extension that hits the buy endpoint at CoinBase
     */
    private static final String BUY_EXT = "/buy";


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for CoinBaseBuy when a cryptocurrency and a fiat currency aren't specified (most likely when
     * the currency is not supported for the given endpoint)
     * @param controller The controller that implements the required methods
     */
    public CoinBaseBuy(final APICallerContract controller) {
        super("Buy", CoinBaseBuy.BUY_EXT, controller);
    }
}
