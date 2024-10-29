package com.company.view.window.error.parse_error;

import com.company.controller.ControllerInterface;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.view.window.error.AbstractJFrameErrorWindow;

/**
 * The error to display if there is an issue with parsing the returned value from the API
 */
public class ParseErrorWindow extends AbstractJFrameErrorWindow implements ParseErrorWindowInterface {
    
    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The message of the error's window
     */
    public static final String MESSAGE = "There appears to be an issue with parsing the data for an endpoint.\n" +
            "This application needs to be updated";

    /**
     * The title of the error's window
     */
    public static final String TITLE = "ERROR: Parse Error";

    /**
     * The width of the error window
     */
    public static final int WIDTH = 1100;

    /**
     * The height of the error window
     */
    public static final int HEIGHT = 100;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the window that displays a parse error
     * @param controller The controller in charge of the window
     */
    public ParseErrorWindow(final ControllerInterface controller) {
        super(controller, ParseErrorWindow.TITLE, ParseErrorWindow.WIDTH, ParseErrorWindow.HEIGHT,
                ParseErrorWindow.MESSAGE);
    }

    /**
     * The constructor for the window that displays a parse error along with the name of the connection that caused the error
     * @param controller The controller in charge of the window
     * @param name The name of the cryptocurrency that caused the error
     */
    public ParseErrorWindow(final ControllerInterface controller, final String name,
                                     final CryptoCurrencies crypto, final FiatCurrencies fiat) {
        super(controller, ParseErrorWindow.TITLE, ParseErrorWindow.WIDTH,
                ParseErrorWindow.HEIGHT, messageWithName(name, crypto, fiat));
    }

    /**
     * Returns the message formatted to hold the cryptocurrency's name that failed parsing
     * @param name The name of the endpoint that failed in parsing
     * @param crypto The cryptocurrency that was being used
     * @param fiat The fiat currency that was being used
     * @return The message formatted to hold the cryptocurrency's name that tried to call a network connection
     */
    private static String messageWithName(final String name, final CryptoCurrencies crypto, final FiatCurrencies fiat) {
        return "There was an error while getting the price for an endpoint. This error occurred while trying to parse " +
                name + " with crypto currency " + crypto.getAbbreviatedName() + " and fiat currency " +
                fiat.getAbbreviatedName() + ".\nPlease inform Alex that this app needs to be updated.";
    }
}
