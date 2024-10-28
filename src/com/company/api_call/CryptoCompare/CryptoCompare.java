package com.company.api_call.CryptoCompare;

import com.company.api_call.APICallerContract;
import com.company.api_call.AbstractAPICaller;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.BadData;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;
import json_simple.JSONObject;

/**
 * The class for the CryptoCompare endpoint
 */
final public class CryptoCompare extends AbstractAPICaller {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The base URL for CryptoCompare requests
     */
    private final static String BASE_URL = "https://min-api.cryptocompare.com/data/price";

    /**
     * The base name for the endpoint
     */
    private final static String NAME = "CryptoCompare";

    /**
     * The accepted cryptocurrencies for CryptoCompare
     */
    private final static CryptoCurrencies[] ACCEPTED_CRYPTOCURRENCIES = {CryptoCurrencies.BTC, CryptoCurrencies.ETH,
            CryptoCurrencies.LTC, CryptoCurrencies.XRP};

    /**
     * The accepted fiat currencies for CryptoCompare
     */
    private final static FiatCurrencies[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrencies.AUD, FiatCurrencies.CAD,
            FiatCurrencies.EUR, FiatCurrencies.GBP, FiatCurrencies.JPY, FiatCurrencies.MXN, FiatCurrencies.NZD,
            FiatCurrencies.PLN, FiatCurrencies.SEK, FiatCurrencies.USD};


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for CryptoCompare when a cryptocurrency and a fiat currency aren't specified (most likely when
     * the currency is not supported for the given endpoint)
     * @param controller The controller that implements the required methods
     */
    public CryptoCompare(final APICallerContract controller) {
        super(CryptoCompare.ACCEPTED_CRYPTOCURRENCIES, CryptoCompare.ACCEPTED_FIAT_CURRENCIES, CryptoCompare.NAME,
                controller);
    }


    /* ************ *
     *   Methods    *
     * ************ */

    @Override
    protected String createURLStringForCall(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported {
        super.throwIfNotAcceptedCurrency(crypto, fiat);

        return CryptoCompare.BASE_URL + "?fsym=" + crypto.getAbbreviatedName() + "&tsyms=" +
                fiat.getAbbreviatedName();
    }

    @Override
    protected double extractPrice(final JSONObject jsonObject, final CryptoCurrencies crypto,
                                  final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported, BadData {
        super.throwIfNotAcceptedCurrency(crypto, fiat);
        double price;
        try {
            // Ok, so I don't know why, but casting it to a regular 'double' wasn't working.
            //  This works, so I'm leaving it
            price = ((Double) jsonObject.get(fiat.getAbbreviatedName()));
        } catch (final ClassCastException e) {
            // Sometimes when the price has too many digits, it gets returned as a long
            price = ((Long) jsonObject.get(fiat.getAbbreviatedName())).doubleValue();
        } catch (final Exception e) {
            throw new BadData(e, this);
        }
        return price;
    }

    /**
     * Returns if the given fiat currency can be used with CryptoCompare
     * @param fiatCurrency The given fiat currency
     * @return If the given fiat currency can be used with CryptoCompare
     */
    public static boolean endpointCanUseFiatCurrency(final FiatCurrencies fiatCurrency)
    {
        return AbstractAPICaller.canUseCurrency(CryptoCompare.ACCEPTED_FIAT_CURRENCIES, fiatCurrency);
    }

    /**
     * Returns if the given cryptocurrency can be used with CryptoCompare
     * @param cryptoCurrency The given cryptocurrency
     * @return If the given cryptocurrency can be used with CryptoCompare
     */
    public static boolean endpointCanUseCryptoCurrency(final CryptoCurrencies cryptoCurrency)
    {
        return AbstractAPICaller.canUseCurrency(CryptoCompare.ACCEPTED_CRYPTOCURRENCIES, cryptoCurrency);
    }
}
