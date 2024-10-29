package com.company.api_call;

import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;

import java.time.LocalDateTime;

/**
 * The abstract API caller interface for all classes which will call APIs
 */
public interface APICallerInterface {

    /**
     * Returns the current price
     * @return The current price
     */
    double getPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    LocalDateTime getLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    boolean isUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    boolean wasLastUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    /**
     * Returns if the API endpoint can use the given cryptocurrency
     * @param cryptoCurrency The cryptocurrency to check
     * @return If the given cryptocurrency can be used with this endpoint
     */
    boolean canUseCryptoCurrency(final CryptoCurrencies cryptoCurrency);

    /**
     * Returns if the API endpoint can use the given fiat currency
     * @param fiatCurrency The fiat currency to check
     * @return If the given fiat currency can be used with this endpoint
     */
    boolean canUseFiatCurrency(final FiatCurrencies fiatCurrency);

    /**
     * Updates the price and notifies the controller
     */
    void updatePriceAndNotify(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    /**
     * Returns the name of the API endpoint
     * @return The name of the API endpoint
     */
    String getName();

}
