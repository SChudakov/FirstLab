package com.sschudakov.abstract_factory.factories;

import com.sschudakov.utils.FileExtensionDeterminer;
import com.sschudakov.utils.JTextAreaCleaner;

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
    public void openFile(JTextArea area) throws FileNotFoundException {

        File pathFile = new File(file.getPath());

        JTextAreaCleaner.cleanJTextArea(area);

        this.scanner = new Scanner(pathFile);

        area.setText(scanner.useDelimiter("\\Z").next());
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


//        try (BufferedReader reader = new BufferedReader(
//                new InputStreamReader(new FileInputStream(pathFile), StandardCharsets.UTF_8))) {
//
//            String line;
//
//            while((line = reader.readLine()) != null){
//                area.append(line + END_OF_LINE);
//            }
//
//        }catch (FileNotFoundException e) {
//            ExceptionRenderer.renderException(area.getRootPane(), e);
//        } catch (IOException e) {
//            ExceptionRenderer.renderException(area.getRootPane(), e);
//        }