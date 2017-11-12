package com.sschudakov;

import com.sschudakov.gui.GUIManager;
import com.sschudakov.tables.table_view.TableModel;
import com.sschudakov.tables.table_view.TableViewManager;


import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

public class Main {

    public static void main(String[] args) {

//        GUIManager manager = new GUIManager();
//        manager.buildGUI();

        TableModel model = new TableModel();
        JTable table = new JTable(model);
        TableViewManager tableViewManager = new TableViewManager(table);
        tableViewManager.buildTableView();
    }

    private static void printCharacters() {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + " " + (char) i);
        }
    }
}
