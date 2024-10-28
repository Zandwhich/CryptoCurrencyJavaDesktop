package com.company.view.window.error.bad_data_error;

import com.company.controller.ControllerInterface;
import com.company.view.window.error.AbstractJFrameErrorWindow;

/**
 * The error window to show if there is a BadData Error
 */
public final class BadDataErrorWindow extends AbstractJFrameErrorWindow implements BadDataWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The title of the error's window
     */
    public static final String TITLE = "ERROR: Bad Data Received";

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
     * The constructor for the window that displays a bad data error along with the apiEndpoint of the connection that
     * caused the error
     * @param controller The controller in charge of the window
     * @param apiEndpoint The apiEndpoint of the cryptocurrency that caused the error
     */
    public BadDataErrorWindow(final ControllerInterface controller, final String apiEndpoint) {
        super(controller, BadDataErrorWindow.TITLE, BadDataErrorWindow.WIDTH, BadDataErrorWindow.HEIGHT,
                messageWithApiEndpointName(apiEndpoint));
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * Returns the message formatted to hold the cryptocurrency's name that tried to call a network connection
     * @param name The name of the cryptocurrency that tried to call a network connection
     * @return The message formatted to hold the cryptocurrency's name that tried to call a network connection
     */
    private static String messageWithApiEndpointName(final String name) {
        return "There was an error reading data from " + name + ".\n" +
                "Please ensure you have the latest version of CryptoCurrency Prices installed and try again.";
    }
}
