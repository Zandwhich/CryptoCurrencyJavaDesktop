package com.company.view.window.main;

import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.view.window.WindowInterface;

import java.time.LocalDateTime;

/**
 * The intractable interface for the main window
 */
public interface MainWindowInterface extends WindowInterface {

    void updatePrice(final String name, final double price, final boolean isSuccessful, final LocalDateTime lastUpdate);

    void setRefreshing(final String name);

    void clear();

    /**
     * Sets the endpoints that this table will have. (This is normally done at the beginning of the program.)
     * @param endpointNames This list of the names of the endpoints. These will be the names that they will be
     *                      referenced when updating them in the future.
     */
    void setEndpoints(final Iterable<String> endpointNames);

    /**
     * Update the fiat currency and cryptocurrency dropdowns to reflect the current crypto selected
     * @param currentCrypto The current cryptocurrency selected
     * @param currentFiat The current fiat currency selected
     */
    void updateDropdowns(final CryptoCurrencies currentCrypto, final FiatCurrencies currentFiat);

}
