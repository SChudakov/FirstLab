package com.sschudakov.tables.table_view;

import org.junit.Test;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableViewManagerTest {

    @Test
    public void buildTableViewTest() {

        String[] columnsNames = new String[]{"Name1", "Name2", "Name3"};
        Object[][] cells = new Object[][]{
                {1, 1, 1},
                {2, 2, 2},
                {3, 3, 3}
        };

        JTable table = new JTable(cells, columnsNames);
        TableViewManager tableViewManager = new TableViewManager(table);
        tableViewManager.builTableView();
    }

}
