package com.company.view.table_pane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * The generic table pane that provides base functionality, from which all table panes extend
 */
abstract public class AbstractJScrollPane extends JScrollPane implements TablePaneInterface {

    /* ************ *
     *    Fields    *
     * ************ */

    /**
     * The columns of the table
     */
    private Vector<String> columns;

    /**
     * The data within the table
     */
    private Vector<Vector<String>> data;

    /**
     * The table
     */
    private JTable table;


    /* ************ *
     * Constructors *
     * ************ */

    /**
     * AbstractJScrollPane constructor
     * @param columns The columns of the table
     * @param data The data of the table
     */
    public AbstractJScrollPane(final Vector<String> columns, final Vector<Vector<String>> data) {
        super();
        this.setup(columns, data);
    }


    /* ************ *
     *    Methods   *
     * ************ */

    // TODO: Alex this is where you left off, you need to update the table so that it will update

    /**
     * Abstracted setup method to be used by all constructors
     * @param columns The columns of the table
     * @param data The data of the table
     */
    private void setup(final Vector<String> columns, final Vector<Vector<String>> data) {
        // TODO: Further, this is where you left off. You're trying to figure out why the columns names get overridden
        System.out.println("So these are the columns:\n" + columns.toString());
        this.columns = columns;
        this.data = data;
        this.table = new JTable(this.data, this.columns);
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.LIGHT_GRAY);
        super.setViewportView(this.table);
    }

    /**
     * TODO: Do I need to put the main table in a lock so that no two threads try to modify it at the same time?
     *       Yes, yes I do.
     *
     * Updates the internal data of the JTable by creating a new TableModel
     * @param data The data as a matrix of strings
     */
    private void updateData(final Vector<Vector<String>> data) {
        final DefaultTableModel tableModel = new DefaultTableModel(this.columns, 0);
        for (final Vector<String> row : data) {
            tableModel.addRow(row);
        }

        this.table.setModel(tableModel);
        super.setViewportView(this.table);
    }

    /**
     * Updates the internal columns of the JTable by creating a new TableModel
     * @param columns The columns as a vector of strings
     */
    private void updateColumns(final Vector<String> columns) {
        final DefaultTableModel tableModel = new DefaultTableModel();
        for (final String column : columns) {
            tableModel.addColumn(column);
        }
        for (final Vector<String> row : this.data) {
            tableModel.addRow(row);
        }

        this.table.setModel(tableModel);
        super.setViewportView(this.table);
    }

    /**
     * Disables editing the table
     */
    protected void disableTableEditable() {
        this.table.setDefaultEditor(Object.class, null);
        this.table.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public Vector<String> getColumns() { return this.columns; }

    @Override
    public Vector<Vector<String>> getData() { return this.data; }

    @Override
    public void setColumns(final Vector<String> columns) {
        this.columns = columns;
        this.updateColumns(this.columns);
        // TODO: Actually set the columns in the JTable (have to play around with this first)
    }

    @Override
    public void setData(final Vector<Vector<String>> data) {
        this.data = data;
        this.updateData(this.data);
    }

}
