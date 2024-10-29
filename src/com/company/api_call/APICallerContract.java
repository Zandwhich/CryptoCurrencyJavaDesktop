package com.company.api_call;

import com.company.tool.enums.Errors;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;

import java.time.LocalDateTime;

/**
 * Whoever wants to use an instantiation of APICallerInterface needs to provide these methods
 */
public interface APICallerContract {

    void notifyPriceSet(final APICallerInterface endpoint, final CryptoCurrencies crypto, final FiatCurrencies fiat,
                        final double price, final boolean isSuccessful,
                        final LocalDateTime lastUpdated);

    void notifyUpdating(final APICallerInterface endpoint, final CryptoCurrencies crypto, final FiatCurrencies fiat,
                        final boolean isUpdating);

    /**
     * Pops up a window that displays an error message
     * @param error The type of error
     * @param name The name of whoever called this error
     */
    void errorDisplay(final Errors error, final String name, final CryptoCurrencies crypto, final FiatCurrencies fiat);
}
