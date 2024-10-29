package com.company.view.window.error.network_error;

import com.company.controller.ControllerInterface;
import com.company.tool.enums.currency.CryptoCurrencies;
import com.company.tool.enums.currency.FiatCurrencies;
import com.company.view.window.error.AbstractJFrameErrorWindow;

/**
 * The error window to show if there is a NetworkError
 */
final public class NetworkErrorWindow extends AbstractJFrameErrorWindow implements NetworkErrorWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The message of the error's window
     */
    public static final String MESSAGE = "There appears to be a network connection error.\n" +
            "Please check your connection and try again.";

    /**
     * The title of the error's window
     */
    public static final String TITLE = "ERROR: Network Connection";

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
     * The constructor for the window that displays a network error
     * @param controller The controller in charge of the window
     */
    public NetworkErrorWindow(final ControllerInterface controller) {
        super(controller, NetworkErrorWindow.TITLE, NetworkErrorWindow.WIDTH, NetworkErrorWindow.HEIGHT,
                NetworkErrorWindow.MESSAGE);
    }

    /**
     * The constructor for the window that displays a network error along with the name of the connection that caused the error
     * @param controller The controller in charge of the window
     * @param name The name of the cryptocurrency that caused the error
     */
    public NetworkErrorWindow(final ControllerInterface controller, final String name) {
        super(controller, NetworkErrorWindow.TITLE, NetworkErrorWindow.WIDTH, NetworkErrorWindow.HEIGHT,
                messageWithName(name));
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * Returns the message formatted to hold the cryptocurrency's name that tried to call a network connection
     * @param name The name of the cryptocurrency that tried to call a network connection
     * @return The message formatted to hold the cryptocurrency's name that tried to call a network connection
     */
    private static String messageWithName(final String name) {
        return "There appears to be a network connection error. This error occurred while trying to connect to " +
                name + ".\nPlease check your connection and try again.";
    }
}
