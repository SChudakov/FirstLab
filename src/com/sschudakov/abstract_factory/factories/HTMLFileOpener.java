package com.sschudakov.abstract_factory.factories;

import com.sschudakov.utils.FileExtensionDeterminer;
import com.sschudakov.utils.JTextAreaCleaner;
import com.sschudakov.utils.JTextAreaTextRenderer;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public class HTMLFileOpener implements FileOpener {

    private com.sschudakov.abstract_factory.products.File file;

    private Scanner scanner;

    public com.sschudakov.abstract_factory.products.File getFile() {
        return file;
    }

    public void setFile(com.sschudakov.abstract_factory.products.File file) {
        this.file = file;
    }

    public HTMLFileOpener(com.sschudakov.abstract_factory.products.File file) {

        validateFile(file);
        this.file = file;
    }

    @Override
    public void openFile() throws FileNotFoundException {

        File pathFile = new File(file.getPath());

        this.scanner = new Scanner(pathFile);

        JTextAreaTextRenderer.renderText(scanner.useDelimiter("\\Z").next());
    }

    private void validateFile(com.sschudakov.abstract_factory.products.File file){

        File pathFile = new File(file.getPath());

        if(!pathFile.exists()){
            throw new IllegalArgumentException("There is no file along the path: " + file.getPath());
        }

        if(!pathFile.isFile()){
            throw new IllegalArgumentException("Path: " + file.getPath() + " points to a directory");
        }

        if(!FileExtensionDeterminer.isHTNLFile(file.getPath())){
            throw new IllegalArgumentException("File: " + file.getPath() + " is not html file");
        }
    }
}