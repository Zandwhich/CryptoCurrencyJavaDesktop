package com.company.controller.main;

import com.company.api_call.APICallerContract;
import com.company.api_call.APICallerInterface;
import com.company.controller.ControllerInterface;
import com.company.tool.enums.Errors;
import com.company.view.button.refresh.RefreshButtonContractInterface;
import com.company.view.combo_box.crypto_dropdown.CryptoDropdownContractInterface;
import com.company.view.combo_box.fiat_dropdown.FiatDropdownContractInterface;
import com.company.view.menu_item.about.AboutMenuItemContractInterface;

import java.util.ArrayList;

/**
 * The interface for specifically the main controller
 */
public interface MainControllerInterface extends
        ControllerInterface,
        AboutMenuItemContractInterface,
        CryptoDropdownContractInterface,
        FiatDropdownContractInterface,
        APICallerContract,
        RefreshButtonContractInterface
{

    /**
     * Update the prices that are listed on the main screen
     */
    void updatePrices();

    /**
     * Gets the list of websites of URLs to hit
     * @return The list of websites of URLs to hit
     */
    ArrayList<APICallerInterface> getEndpointList();

    /**
     * Pops up a window that displays an error message
     * @param error The type of error
     */
    void errorDisplay(final Errors error);
}
