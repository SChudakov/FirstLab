package com.sschudakov.abstract_factory.factories.closers;

import com.sschudakov.abstract_factory.factories.savers.TableSaver;
import com.sschudakov.utils.OptionConfirmer;

import javax.swing.*;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class TableFileCloser implements Closer {

    private TableSaver saver;

    public TableSaver getSaver() {
        return saver;
    }

    public TableFileCloser(TableSaver saver) {
        this.saver = saver;
    }

    @Override
    public boolean close() {
        int option = OptionConfirmer.confirmOption(this.saver.getTable().getParent(), "Do you want to save changes");

        if (option == JOptionPane.YES_OPTION) {
            return this.saver.save();
        }

        if (option == JOptionPane.NO_OPTION) {
            return true;
        }

//      JOptionPane.CANCEL_OPTION ||JOptionPane.CLOSED_OPTION
        return false;
    }
}
