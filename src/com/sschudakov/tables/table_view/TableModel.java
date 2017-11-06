package com.sschudakov.tables.table_view;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableModel extends DefaultTableModel {

    private static final int NUM_OF_ROWS = 50;
    private static final int NUM_OF_COLUMNS = 10;
    private Object[][] tableCells;

    public TableModel() {
        this.tableCells = new Object[NUM_OF_ROWS][NUM_OF_COLUMNS];
    }

    @Override
    public int getRowCount() {
        return NUM_OF_ROWS;
    }

    @Override
    public int getColumnCount() {
        return NUM_OF_COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.tableCells[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.tableCells[rowIndex][columnIndex] =  aValue;
        super.fireTableCellUpdated(rowIndex, columnIndex);
    }

}
