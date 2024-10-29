package com.company.view.window.error;

import com.company.controller.ControllerInterface;
import com.company.view.button.close.CloseJButton;
import com.company.view.window.AbstractJFrameWindow;

import javax.swing.*;

/**
 * The generic window for any error
 */
public abstract class AbstractJFrameErrorWindow extends AbstractJFrameWindow implements ErrorWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The panel that holds all the components of the page together
     */
    private final JPanel panel = new JPanel();


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the window that displays an error
     * @param controller The controller in charge of the window
     * @param title The title of the window
     * @param width The width of the window
     * @param height The height of the window
     * @param message The error message (body) to display
     */
    public AbstractJFrameErrorWindow(final ControllerInterface controller, final String title, final int width,
                                     final int height, final String message) {
        super(title, width, height);
        this.setup(controller, message);
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * The redundant setup that is run for each constructor
     * @param message The error message (body) of the window
     */
    private void setup(final ControllerInterface controller, final String message) {
        // The label that holds the message
        panel.add(new JLabel(message));

        // The button that can close the screen
        panel.add(new CloseJButton(this));

        this.add(panel);

        // TODO: Figure out how to get the window to properly display. Is there an update UI thread I'm missing? When
        //       I resize the window after it appears, only then can I see the text and button, not before
    }

}
