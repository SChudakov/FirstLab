package com.sschudakov.abstract_factory.factories.openers;

import com.sschudakov.utils.JTextAreaTextRenderer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public class TextFileOpener implements Opener {

    private JTextArea area;
    private File file;
    private Scanner scanner;

    public TextFileOpener(File file, JTextArea area) {
        validateFile(file);
        this.file = file;
        this.area = area;
    }

    @Override
    public void openFile() throws FileNotFoundException {
        this.scanner = new Scanner(this.file);

        System.out.println("\n" + this.file.toString() + "\n");
        if (this.file.length() != 0) {
            JTextAreaTextRenderer.renderText(this.scanner.useDelimiter("\\Z").next(), area);
        } else {
            System.out.println("is empty");
        }
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException("There is no file along the path: " + file.getPath());
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("Path: " + file.getPath() + " points to a directory");
        }
    }
}
