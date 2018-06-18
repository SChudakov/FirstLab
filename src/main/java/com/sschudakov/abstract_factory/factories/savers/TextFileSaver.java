package com.sschudakov.abstract_factory.factories.savers;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 23.09.2017.
 */
public class TextFileSaver implements Saver {

    private JTextArea area;
    private File file;

    public JTextArea getArea() {
        return area;
    }

    public File getFile() {
        return file;
    }

    public TextFileSaver(JTextArea area, File file) {
        this.area = area;
        this.file = file;
    }

    @Override
    public boolean save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file))) {
            writer.write(this.area.getText());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("File failed to be saved correctly because of: " + e.getMessage());
        }
        return true;
    }
}
