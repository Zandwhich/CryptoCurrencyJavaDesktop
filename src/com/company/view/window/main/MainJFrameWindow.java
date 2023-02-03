package com.company.view.window.main;

import com.company.tools.enums.currency.CryptoCurrencies;
import com.company.tools.enums.currency.FiatCurrencies;
import com.company.controller.main.MainControllerInterface;
import com.company.api_call.APICallerInterface;
import com.company.view.combo_box.crypto_dropdown.CryptoDropdownJComboBox;
import com.company.view.combo_box.fiat_dropdown.FiatDropdownJComboBox;
import com.company.view.menu_bar.main_menu.MainJMenuBar;
import com.company.view.table_pane.main_table.MainJScrollPane;
import com.company.view.table_pane.main_table.MainTablePaneInterface;
import com.company.view.button.refresh.RefreshJButton;
import com.company.view.button.refresh.RefreshButtonInterface;
import com.company.view.window.AbstractJFrameWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The main window to display for the application
 */
final public class MainJFrameWindow extends AbstractJFrameWindow implements MainWindowInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /* Public */

    /**
     * The title of the main window
     */
    public final static String TITLE = "CryptoCurrency Prices";

    /**
     * The default width of the main window
     */
    public final static int DEFAULT_WIDTH = 1300;

    /**
     * The default height of the main window
     */
    public final static int DEFAULT_HEIGHT = 700;

    /**
     * The default x position of the window
     */
    public final static int DEFAULT_X = 155;

    /**
     * The default y position of the window
     */
    public final static int DEFAULT_Y = 58;

    /**
     * The default visibility of the main window
     */
    public final static boolean DEFAULT_VISIBILITY = true;

    /**
     * The text to display above the fiat currency dropdown
     */
    public final static String FIAT_DROPDOWN_TEXT = "Fiat Currency";

    /**
     * The text to display above the cryptocurrency dropdown
     */
    public final static String CRYPTO_DROPDOWN_TEXT = "Cryptocurrency";

    /* Private */

    /**
     * The panel which holds all the other ui components
     */
    private final JPanel panel = new JPanel();

    /**
     * The main controller, which controls this window
     */
    private MainControllerInterface mainController;

    /**
     * The data to be displayed in the main table
     */
    private final Vector<Vector<String>> data = new Vector<>();

    /**
     * The main table that displays all the information
     */
    private MainTablePaneInterface table;

    /**
     * The dropdown to choose the fiat currency
     */
    private FiatDropdownJComboBox fiatDropdown;

    /**
     * The dropdown to choose the cryptocurrency
     */
    private CryptoDropdownJComboBox cryptoDropdown;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The default constructor for the main window
     * @param mainController The MainController of the application
     */
    public MainJFrameWindow(final MainControllerInterface mainController) {
        super(mainController, MainJFrameWindow.TITLE, MainJFrameWindow.DEFAULT_WIDTH, MainJFrameWindow.DEFAULT_HEIGHT,
                JFrame.EXIT_ON_CLOSE, MainJFrameWindow.DEFAULT_VISIBILITY);
        this.setup();
    }


    /* ************ *
     *    Methods   *
     * ************ */

    /**
     * The general setup method that is used for maximum abstraction
     */
    private void setup() {
        super.setLocation(MainJFrameWindow.DEFAULT_X, MainJFrameWindow.DEFAULT_Y);

        this.mainController = (MainControllerInterface) super.getController();
        this.table = new MainJScrollPane(this.data);

        super.setJMenuBar(new MainJMenuBar(this.mainController));

        final RefreshButtonInterface refreshButton = new RefreshJButton(this.mainController, this);
        this.fiatDropdown = new FiatDropdownJComboBox(FiatCurrencies.toStringArray(), this.mainController);
        this.cryptoDropdown = new CryptoDropdownJComboBox(CryptoCurrencies.toStringArray(), this.mainController);

        final JTextField fiat_dropdown_text = new JTextField(MainJFrameWindow.FIAT_DROPDOWN_TEXT);
        fiat_dropdown_text.setEditable(false);
        final JTextField crypto_dropdown_text = new JTextField(MainJFrameWindow.CRYPTO_DROPDOWN_TEXT);
        crypto_dropdown_text.setEditable(false);

        this.panel.add(fiat_dropdown_text);
        this.panel.add(this.fiatDropdown);
        this.panel.add(crypto_dropdown_text);
        this.panel.add(this.cryptoDropdown);
        // TODO: Figure out how to resize the image
        this.panel.add((JButton) refreshButton);
        this.panel.add((JScrollPane) this.table);
        this.add(this.panel);

        this.updatePrices();
        this.setVisible(MainJFrameWindow.DEFAULT_VISIBILITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDropdowns() {
        this.fiatDropdown.setSelectedItem(this.mainController.getCurrentFiat().getAbbreviatedName());
        this.cryptoDropdown.setSelectedItem(this.mainController.getCurrentCrypto().getAbbreviatedName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePrices() {
        final ArrayList<APICallerInterface> websites = this.mainController.getWebsiteList();
        this.data.clear();

        // TODO: Clean this up a bit?
        for (APICallerInterface website : websites) {
            final Vector<String> websiteVec = new Vector<>();
            websiteVec.add(website.getName());
            websiteVec.add("" + website.getPrice());
            this.data.add(websiteVec);
        }
        this.table.setData(this.data);
    }

    /**
     * Closes the window and the application
     */
    @Override
    public void close() { }

}
