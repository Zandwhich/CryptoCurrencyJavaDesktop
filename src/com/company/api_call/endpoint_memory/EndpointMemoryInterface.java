package com.company.api_call.endpoint_memory;

import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.tool.exception.currency_not_supported.CryptoCurrencyNotSupported;
import com.company.tool.exception.currency_not_supported.FiatCurrencyNotSupported;

import java.time.LocalDateTime;

public interface EndpointMemoryInterface {

    double getPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    void setPrice(final CryptoCurrencies crypto, final FiatCurrencies fiat, final double price)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    boolean isUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    void setUpdating(final CryptoCurrencies crypto, final FiatCurrencies fiat, final boolean isUpdating)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    LocalDateTime getLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    void setLastSuccessfulUpdated(final CryptoCurrencies crypto, final FiatCurrencies fiat, final LocalDateTime lastUpdated)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    boolean wasLastUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;

    void setWasLastUpdateSuccessful(final CryptoCurrencies crypto, final FiatCurrencies fiat, final boolean successful)
            throws CryptoCurrencyNotSupported, FiatCurrencyNotSupported;
}
