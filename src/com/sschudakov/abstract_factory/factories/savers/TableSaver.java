package com.sschudakov.abstract_factory.factories.savers;

import com.sschudakov.utils.ExceptionRenderer;

import javax.swing.*;
import java.io.*;

/**
 * Created by Semen Chudakov on 09.11.2017.
 */
public class TableSaver implements Saver {

    private JTable table;
    private File file;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public TableSaver(File file) {
        this.file = file;
    }

    @Override
    public void save() {
        if (this.table != null) {
            if (ensureFileIsNotNull()) {
                try {
                    saveTable(this.table, this.file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    ExceptionRenderer.renderException(this.table.getParent(), e1);
                }
            }
        } else {
            throw new RuntimeException("table is not opened");
        }
    }

    private boolean ensureFileIsNotNull() {
        if (this.file != null) {
            return true;
        }
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.showSaveDialog(this.table.getParent());
        this.file = jFileChooser.getSelectedFile();

        if (this.file != null) {
            return true;
        }
        return false;
    }

    private static void saveTable(JTable table, File file) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        outputStream.writeObject(table);
        outputStream.close();
    }
}
