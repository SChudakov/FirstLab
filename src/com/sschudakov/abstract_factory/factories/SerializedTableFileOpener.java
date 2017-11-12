package com.sschudakov.abstract_factory.factories;

import com.sschudakov.tables.table_view.TableModel;
import com.sschudakov.tables.table_view.TableViewManager;
import com.sschudakov.utils.FileExtensionDeterminer;

import javax.swing.*;
import java.io.*;

/**
 * Created by Semen Chudakov on 05.11.2017.
 */
public class SerializedTableFileOpener implements FileOpener {

    private com.sschudakov.abstract_factory.products.File file;

    public SerializedTableFileOpener(com.sschudakov.abstract_factory.products.File file) {

        validateFile(file);
        this.file = file;
    }

    @Override
    public void openFile() throws IOException, ClassNotFoundException {
        JTable table = readTable(this.file.getPath());
        TableViewManager tableViewManager = new TableViewManager(table);
        tableViewManager.buildTableView();
    }

    private void validateFile(com.sschudakov.abstract_factory.products.File file) {

        java.io.File pathFile = new java.io.File(file.getPath());

        if (!pathFile.exists()) {
            throw new IllegalArgumentException("There is no file along the path: " + file.getPath());
        }

        if (!pathFile.isFile()) {
            throw new IllegalArgumentException("Path: " + file.getPath() + " points to a directory");
        }

        if (!FileExtensionDeterminer.isExcelTableFile(file.getPath())) {
            throw new IllegalArgumentException("File: " + file.getPath() + " is not excel table file");
        }
    }

    private static JTable readTable(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        return readTable(file);
    }

    private static JTable readTable(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(
                new FileInputStream(file)));
        JTable result = (JTable) inputStream.readObject();
        inputStream.close();
        return result;
    }
}
