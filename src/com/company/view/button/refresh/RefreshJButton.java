package com.company.view.button.refresh;

import com.company.controller.main.MainControllerInterface;
import com.company.view.button.AbstractJButtonButton;
import com.company.view.window.WindowInterface;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The refresh button that is displayed on the main page
 */
final public class RefreshJButton extends AbstractJButtonButton implements RefreshButtonInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The path to the image for the refresh button
     */
    public static final String IMAGE_PATH = "src/resources/images/refresh_button_image.PNG";

    /**
     * The controller that implements the contract for the refresh button
     */
    private final RefreshButtonContractInterface controller;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * A constructor for the refresh button
     * @param controller The controller that holds and subscribes to the button that will be refreshed
     */
    public RefreshJButton(final RefreshButtonContractInterface controller) {
        super(RefreshJButton.IMAGE_PATH);
        this.controller = controller;

        // TODO: This doesn't work, sadly...
        super.setMaximumSize(new Dimension(100, 100));
        super.setMinimumSize(new Dimension(10, 10));
    }


    /* ************ *
     *   Methods    *
     * ************ */

    /**
     * The action to do on pressing. It will tell the controller to refresh
     * @param event Because of the nature of the method, an ActionEvent is required
     */
    @Override
    protected void onClick(ActionEvent event) {
        this.controller.refresh();
    }

}
