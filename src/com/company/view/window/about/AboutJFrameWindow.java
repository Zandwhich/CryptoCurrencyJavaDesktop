package com.company.view.window.about;

import com.company.controller.ControllerInterface;
import com.company.view.window.AbstractJFrameWindow;

import javax.swing.*;
import java.awt.*;

/**
 * The instantiation for the About Window
 */
public final class AboutJFrameWindow extends AbstractJFrameWindow implements AboutWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The title of the About window
     */
    public static final String TITLE = "About CryptoCurrency Prices";

    /**
     * The first part of the message of the About window (for now)
     * TODO: Figure out what to do here properly. Also, figure out how to do proper versioning
     */
    public static final String MESSAGE_1 = "CryptoCurrency Prices v0.2";

    /**
     * The second part of the message of the About window (for now)
     */
    public static final String MESSAGE_2 = "Alex Zdanowicz 2017-2023";

    /**
     * The width of the About window
     */
    public static final int WIDTH = 300;

    /**
     * The height of the About window
     */
    public static final int HEIGHT = (int) (AboutJFrameWindow.WIDTH / 1.618);


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the About page
     */
    public AboutJFrameWindow() {
        super(AboutJFrameWindow.TITLE, AboutJFrameWindow.WIDTH, AboutJFrameWindow.HEIGHT);

        this.setup();
    }


    /* ************ *
     *   Methods    *
     * ************ */

    /**
     * Sets up the parts of the window and lays out the text so that it is very nice (and resizes with the window).
     */
    private void setup() {
        this.getContentPane().setLayout(new GridBagLayout());
        final GridBagConstraints c = new GridBagConstraints();

        final JLabel message1 = new JLabel(AboutJFrameWindow.MESSAGE_1);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        this.getContentPane().add(message1, c);

        final JLabel message2 = new JLabel(AboutJFrameWindow.MESSAGE_2);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 3;
        c.weighty = 0.3;
        c.anchor = GridBagConstraints.PAGE_END;
        this.getContentPane().add(message2, c);
    }

}
