package com.company.view.window.windows.error;

import com.company.controller.ControllerInterface;
import com.company.view.window.AbstractJFrameWindow;

import javax.swing.*;

/**
 * TODO: Fill in
 */
public abstract class AbstractJFrameErrorWindow extends AbstractJFrameWindow implements ErrorWindowInterface {

    /****************
     *    Fields    *
     ****************/

    /**
     * The error message (body) to display
     */
    private String message;

    /**
     * The label that holds the message
     */
    private JLabel label;

    /****************
     * Constructors *
     ****************/

    /**
     * TODO: The constructor for the window that displays an error
     * @param controller The controller in charge of the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     * @param message The error message (body) to display
     */
    public AbstractJFrameErrorWindow(ControllerInterface controller, String title, int width, int height,
                                     String message) {
        super(controller, title, width, height);
        this.setup(message);
    }//end AbstractJFrameError()

    /****************
     *    Methods   *
     ****************/

    /* Private */

    /**
     * The redundant setup that is run for each constructor
     * @param message The error message (body) of the window
     */
    private void setup(String message) {
        this.message = message;
        this.label = new JLabel(message);
        this.add(this.label);
    }//end setup()

    /* Protected */

    /* Public */

    // Getters

    // Setters

    // Others

}//end AbstractJFrameError
