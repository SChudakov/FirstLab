package com.sschudakov.tables.utils;

import com.sschudakov.utils.OptionConfirmer;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class TableViewCloser {
    public static boolean closeTableView(JTable table) {
        int option = OptionConfirmer.confirmOption(table.getParent(), "Do you want to save changes");

        if (option == JOptionPane.YES_OPTION) {
            TableSaver.saveTable(table);
            return true;
        }

        if (option == JOptionPane.NO_OPTION) {
            return true;
        }

//      JOptionPane.CANCEL_OPTION ||JOptionPane.CLOSED_OPTION
        return false;
    }
}
