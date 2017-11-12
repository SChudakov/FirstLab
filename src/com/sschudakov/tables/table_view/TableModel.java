package com.sschudakov.tables.table_view;

import com.sschudakov.tables.utils.ToDimensionalArrayOutputer;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableModel extends DefaultTableModel {

    private static final int NUM_OF_ROWS = 10;
    private static final int NUM_OF_COLUMNS = 10;

    private int numOfRows = NUM_OF_ROWS;
    private int numOfColumns = NUM_OF_COLUMNS;

    private Object[][] tableCells;

    public TableModel() {
        super(NUM_OF_ROWS, NUM_OF_COLUMNS);
        this.tableCells = new Object[NUM_OF_ROWS][NUM_OF_COLUMNS];
    }

    @Override
    public int getRowCount() {
        return this.numOfRows;
    }

    @Override
    public int getColumnCount() {
        return this.numOfColumns;
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

    @Override
    public void addRow(Object[] rowData) {

        System.out.println("add row");

        Object[][] newTable = new Object[getRowCount() + 1][getColumnCount()];

        ToDimensionalArrayOutputer.ouputArray(tableCells);
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                newTable[i][j] = this.tableCells[i][j];
            }
        }
        for (int i = 0; i < getColumnCount(); i++) {
            newTable[getRowCount()][i] = rowData[i];
        }
        ToDimensionalArrayOutputer.ouputArray(newTable);

        this.tableCells = newTable;
        this.numOfRows++;
        System.out.println("rows count: " + getRowCount());
        System.out.println("columns count: " + getColumnCount());
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    @Override
    public void addColumn(Object columnName) {

        System.out.println("add column");

        Object[][] newTable = new Object[getRowCount()][getColumnCount() + 1];

        ToDimensionalArrayOutputer.ouputArray(tableCells);
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                newTable[i][j] = this.tableCells[i][j];
            }
        }
        for (int i = 0; i < getRowCount(); i++) {
            newTable[i][getColumnCount()] = new TableCell();
        }
        ToDimensionalArrayOutputer.ouputArray(newTable);

        this.tableCells = newTable;
        this.numOfColumns++;
        System.out.println("rows count: " + getRowCount());
        System.out.println("columns count: " + getColumnCount());
        fireTableStructureChanged();
    }

    @Override
    public void removeRow(int rowIndex) {
        System.out.println("remove row");

        Object[][] newTable = new Object[getRowCount() - 1][getColumnCount()];

        ToDimensionalArrayOutputer.ouputArray(tableCells);
        //copy part of the array, that is before selected row
        for (int i = 0; i < rowIndex; i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                newTable[i][j] = this.tableCells[i][j];
            }
        }
        //copy part of the array, that is after selected row
        for (int i = rowIndex + 1; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                newTable[i - 1][j] = this.tableCells[i][j];
            }
        }
        ToDimensionalArrayOutputer.ouputArray(newTable);

        this.tableCells = newTable;
        this.numOfRows--;
        System.out.println("rows count: " + getRowCount());
        System.out.println("columns count: " + getColumnCount());
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void removeColumn(int columnIndex) {
        System.out.println("remove column");

        Object[][] newTable = new Object[getRowCount()][getColumnCount() - 1];

        ToDimensionalArrayOutputer.ouputArray(tableCells);
        //copy part of the array, that is before selected row
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < columnIndex; j++) {
                newTable[i][j] = this.tableCells[i][j];
            }
        }
        //copy part of the array, that is after selected row
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = columnIndex + 1; j < getColumnCount(); j++) {
                newTable[i][j - 1] = this.tableCells[i][j];
            }
        }
        ToDimensionalArrayOutputer.ouputArray(newTable);

        this.tableCells = newTable;
        this.numOfColumns--;
        System.out.println("rows count: " + getRowCount());
        System.out.println("columns count: " + getColumnCount());
        fireTableStructureChanged();
    }
}
