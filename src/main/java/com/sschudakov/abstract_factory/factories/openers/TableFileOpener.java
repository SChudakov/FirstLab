package com.sschudakov.abstract_factory.factories.openers;

import com.sschudakov.tables.table_view.TableModel;
import com.sschudakov.tables.table_view.TableViewManager;

import javax.swing.*;
import java.io.*;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class TableFileOpener implements Opener {

    private File file;
    private JTable table;
    private TableViewManager viewManager;

    public JTable getTable() {
        return table;
    }

    public TableViewManager getViewManager() {
        return viewManager;
    }

    public TableFileOpener(File file) {
//        validateFile(file);
        this.file = file;
    }

    @Override
    public void openFile() throws IOException, ClassNotFoundException {
        JTable readTable = readTable(this.file);
        this.table = readTable;
        TableViewManager tableViewManager = new TableViewManager(readTable);
        this.viewManager = tableViewManager;
        tableViewManager.buildTableView();
    }

//    private void validateFile(File file) {
//        if (!file.exists()) {
//            throw new IllegalArgumentException("There is no file along the path: " + file.getPath());
//        }
//        if (!file.isFile()) {
//            throw new IllegalArgumentException("Path: " + file.getPath() + " points to a directory");
//        }
//    }

    private static JTable readTable(File file) throws IOException, ClassNotFoundException {
        if (file == null) {
            TableModel model = new TableModel();
            JTable table = new JTable(model);
            return table;
        }
        ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(file)));
        JTable result = (JTable) inputStream.readObject();
        inputStream.close();
        return result;
    }
}
