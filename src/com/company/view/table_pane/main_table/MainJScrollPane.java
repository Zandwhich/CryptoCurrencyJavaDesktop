package com.company.view.table_pane.main_table;

import com.company.view.table_pane.AbstractJScrollPane;

import java.util.Arrays;
import java.util.Vector;

/**
 * The main table that displays the prices
 */
final public class MainJScrollPane extends AbstractJScrollPane implements MainTablePaneInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The array that the main table column headers are made from because Vectors are dumb
     */
    private static final String[] DEFAULT_COLUMN_HEADERS_ARRAY =
            { "Website", "Price", "Status", "Last Successfully Updated" };

    /**
     * The default column headers for the main table
     */
    public static final Vector<String> DEFAULT_COLUMN_HEADERS =
            new Vector<>(Arrays.asList(MainJScrollPane.DEFAULT_COLUMN_HEADERS_ARRAY));


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * The constructor for the main table pane
     * @param data The data of all the prices of the cryptocurrencies and whatnot
     */
    public MainJScrollPane(final Vector<Vector<String>> data) {
        super(MainJScrollPane.DEFAULT_COLUMN_HEADERS, data);
        super.disableTableEditable();
    }

}
