package com.company.view.window.error.endpoint_update_error;

import com.company.controller.ControllerInterface;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.view.window.error.AbstractJFrameErrorWindow;

/**
 * The error to display if there is an error updating an endpoint
 */
final public class EndpointUpdateErrorWindow extends AbstractJFrameErrorWindow implements EndpointUpdateErrorWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The message of the error's window
     */
    public static final String MESSAGE = "There was an error updating an endpoint.\n" +
            "Please check your connection and try again.";

    /**
     * The title of the error's window
     */
    public static final String TITLE = "ERROR: Endpoint Update Failure";

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
     * The constructor for the window that displays an endpoint update error
     * @param controller The controller in charge of the window
     */
    public EndpointUpdateErrorWindow(final ControllerInterface controller) {
        super(controller, EndpointUpdateErrorWindow.TITLE, EndpointUpdateErrorWindow.WIDTH, EndpointUpdateErrorWindow.HEIGHT,
                EndpointUpdateErrorWindow.MESSAGE);
    }

    /**
     * The constructor for the window that displays an endpoint update error along with the name of the connection that caused the error
     * @param controller The controller in charge of the window
     * @param name The name of the cryptocurrency that caused the error
     */
    public EndpointUpdateErrorWindow(final ControllerInterface controller, final String name,
                                     final CryptoCurrencies crypto, final FiatCurrencies fiat) {
        super(controller, EndpointUpdateErrorWindow.TITLE, EndpointUpdateErrorWindow.WIDTH,
                EndpointUpdateErrorWindow.HEIGHT, messageWithName(name, crypto, fiat));
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * Returns the message formatted to hold the cryptocurrency's name that tried to call a network connection
     * @param name The name of the endpoint that tried to call a network connection
     * @param crypto The name of the cryptocurrency that was being used
     * @param fiat The name of the fiat currency that was being used
     * @return The message formatted to hold the cryptocurrency's name that tried to call a network connection
     */
    private static String messageWithName(final String name, final CryptoCurrencies crypto, final FiatCurrencies fiat) {
        return "There was an error while getting the price for an endpoint. This error occurred while trying to connect " +
                "to " + name + " with crypto currency " + crypto.getAbbreviatedName() + " and fiat currency " +
                fiat.getAbbreviatedName() + ".\nPlease check your connection and try again.";
    }

}
