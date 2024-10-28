package com.company.view.window.error.default_error;

import com.company.controller.ControllerInterface;
import com.company.view.window.error.AbstractJFrameErrorWindow;

/**
 * The error window to show if there is a DefaultError
 */
public class DefaultErrorWindow extends AbstractJFrameErrorWindow implements DefaultErrorInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The title of the error's window
     */
    public static final String TITLE = "ERROR: Unknown Error";

    public static final String MESSAGE = "There was an unknown that occurred";

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
     * The constructor for the window that displays a default error
     * @param controller The controller in charge of the window
     */
    public DefaultErrorWindow(final ControllerInterface controller) {
        super(controller, DefaultErrorWindow.TITLE, DefaultErrorWindow.WIDTH, DefaultErrorWindow.HEIGHT,
                DefaultErrorWindow.MESSAGE);
    }
}
