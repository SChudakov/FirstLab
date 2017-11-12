package com.sschudakov.tables.utils;

import com.sschudakov.utils.ExceptionRenderer;

import javax.swing.*;
import java.io.*;

/**
 * Created by Semen Chudakov on 09.11.2017.
 */
public class TableSaver {

    private static JFileChooser jFileChooser = new JFileChooser();

    public static void saveTable(JTable table) {
        jFileChooser.showSaveDialog(table.getParent());
        File chosenFile = jFileChooser.getSelectedFile();
        if (chosenFile != null) {
            try {
                TableSaver.saveTable(table, chosenFile);
            } catch (IOException e1) {
                e1.printStackTrace();
                ExceptionRenderer.renderException(table.getParent(), e1);
            }
        }
    }

    private static void saveTable(JTable table, File file) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        outputStream.writeObject(table);
        outputStream.close();
    }
}
