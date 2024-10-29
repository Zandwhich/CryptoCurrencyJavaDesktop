package com.company.api_call.CoinBase;

import com.company.api_call.APICallerContract;
import com.company.api_call.AbstractAPICaller;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.BadData;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;
import json_simple.JSONObject;

/**
 * The basic class for all CoinBase requests
 */
public abstract class AbstractCoinBase extends AbstractAPICaller {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The cryptocurrencies that CoinBase uses
     */
    private static final CryptoCurrencies[] ACCEPTED_CRYPTO_CURRENCIES = {CryptoCurrencies.BTC, CryptoCurrencies.ETH,
            CryptoCurrencies.LTC, CryptoCurrencies.XRP};

    /**
     * The fiat currencies that CoinBase uses
     */
    private static final FiatCurrencies[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrencies.AUD, FiatCurrencies.CAD,
            FiatCurrencies.EUR, FiatCurrencies.JPY, FiatCurrencies.MXN, FiatCurrencies.NZD, FiatCurrencies.PLN,
            FiatCurrencies.SEK, FiatCurrencies.USD};

    /**
     * The base URL for CoinBase requests
     */
    private static final String BASE_URL = "https://api.coinbase.com/v2/prices/";

    private final String urlExt;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for AbstractCoinBase when a cryptocurrency and a fiat currency aren't specified (most likely when
     * the currency is not supported for the given endpoint)
     * @param name       The name of the specific implementation of the AbstractCoinBase endpoint
     * @param controller The controller that implements the required methods
     */
    public AbstractCoinBase(final String name, final String urlExt, final APICallerContract controller) {
        super(AbstractCoinBase.ACCEPTED_CRYPTO_CURRENCIES, AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES, "Coinbase "
                + name, controller);
        this.urlExt = urlExt;
    }


    /* ************ *
     *   Methods    *
     * ************ */

    @Override
    protected double extractPrice(final JSONObject jsonObject, final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported, BadData {
        super.throwIfNotAcceptedCurrency(crypto, fiat);

        try {
            return Double.parseDouble(((String) ((JSONObject) jsonObject.get("data")).get("amount")));
        } catch (final Exception e) {
            throw new BadData(e, this);
        }
    }

    @Override
    protected String createURLStringForCall(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        this.throwIfNotAcceptedCurrency(crypto, fiat);

        return AbstractCoinBase.BASE_URL + crypto.getAbbreviatedName() + "-" + fiat.getAbbreviatedName() + this.urlExt;
    }
}
