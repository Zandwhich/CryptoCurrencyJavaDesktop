package com.company.view.window;

import javax.swing.*;
import java.awt.event.WindowEvent;

/**
 * The abstract window class which uses Java's JFrame
 */
public abstract class AbstractJFrameWindow extends JFrame implements WindowInterface {

    /* ************ *
     * Constructors *
     * ************ */

    /**
     * Constructor for an AbstractJFrameWindow
     * @param title The title of the window
     * @param width The width (in pixels) of the window
     * @param height The height (in pixels) of the window
     */
    public AbstractJFrameWindow(final String title, final int width, final int height) {
        this.setup(title, width, height, JFrame.DISPOSE_ON_CLOSE, true);
    }

    /**
     * Constructor for an AbstractJFrameWindow
     * @param title The title of the window
     * @param width The width (in pixels) of the window
     * @param height The height (in pixels) of the window
     * @param closeOperation The operation to be carried out on the closing of the window (should be one of:
     *                       JFrame.EXIT_ON_CLOSE, JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE, or
     *                       JFrame.DO_NOTHING_ON_CLOSE)
     * @param isVisible If the window is visible or not
     */
    public AbstractJFrameWindow(final String title, final int width, final int height, final int closeOperation,
                                final boolean isVisible) {
        this.setup(title, width, height, closeOperation, isVisible);
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * The redundant setup that is run for each constructor
     * @param title The title of the window
     * @param width The width (in pixels) of the window
     * @param height The height (in pixels) of the window
     * @param closeOperation The operation to be carried out on the closing of the window (should be one of:
     *                       JFrame.EXIT_ON_CLOSE, JFrame.DISPOSE_ON_CLOSE, JFrame.HIDE_ON_CLOSE, or
     *                       JFrame.DO_NOTHING_ON_CLOSE)
     * @param isVisible If the window is visible or not
     */
    private void setup(final String title, final int width, final int height, final int closeOperation,
                       final boolean isVisible) {

        // Setting up the window
        super.setTitle(title);
        super.setSize(width, height);
        super.setDefaultCloseOperation(closeOperation);
        super.setVisible(isVisible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTitle(final String title) { super.setTitle(title); }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocation(final int x, final int y) {
        super.setLocation(x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        super.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
