package com.sschudakov.operations;

import com.sschudakov.utils.JTextAreaCleaner;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 23.09.2017.
 */
public class FileSaver {

    public static void saveFile(JTextArea area, String path) {
        File pathFile = new File(path);
        saveFile(area, pathFile);
        JTextAreaCleaner.cleanJTextArea(area);
    }

    public static void saveFile(JTextArea area, File file) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(area.getText());
        } catch (IOException e) {
            throw new RuntimeException("File failed to be saved correctly because of: " + e.getMessage());
        }
    }
}
