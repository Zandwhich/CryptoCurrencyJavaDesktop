package com.company.api_call.CoinBase;

import com.company.api_call.APICallerContract;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;

/**
 * The implementation for the sell endpoint for CoinBase
 */
final public class CoinBaseSell extends AbstractCoinBase {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The url extension that hits the sell endpoint at CoinBase
     */
    private final static String SELL_EXT = "/sell";


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for CoinBaseSell when a cryptocurrency and a fiat currency aren't specified (most likely when
     * the currency is not supported for the given endpoint)
     * @param controller The controller that implements the required methods
     */
    public CoinBaseSell(final APICallerContract controller) {
        super("Sell", CoinBaseSell.SELL_EXT, controller);
    }
}
