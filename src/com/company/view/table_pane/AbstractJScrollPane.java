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

    /**
     * Abstracted setup method to be used by all constructors
     * @param columns The columns of the table
     * @param data The data of the table
     */
    private  void setup(final Vector<String> columns, final Vector<Vector<String>> data) {
        this.columns = columns;
        this.data = data;
        this.table = new JTable(this.data, this.columns);
        this.table.setShowGrid(true);
        this.table.setGridColor(Color.LIGHT_GRAY);
        super.setViewportView(this.table);
    }

    /**
     * Updates the internal data of the JTable by creating a new TableModel
     * @param data The data as a matrix of strings
     */
    private void updateData(final Vector<Vector<String>> data) {
        final DefaultTableModel tableModel = new DefaultTableModel();
        for (int i = 0; i < this.table.getModel().getColumnCount(); i++) {
            tableModel.addColumn(this.table.getColumnName(i));
        }
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<String> getColumns() { return this.columns; }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<Vector<String>> getData() { return this.data; }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColumns(final Vector<String> columns) {
        this.columns = columns;
        this.updateColumns(this.columns);
        // TODO: Actually set the columns in the JTable (have to play around with this first)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(final Vector<Vector<String>> data) {
        this.data = data;
        this.updateData(this.data);
    }

}
