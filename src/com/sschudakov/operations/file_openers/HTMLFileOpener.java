package com.sschudakov.operations.file_openers;

import com.sschudakov.utils.ExceptionRenderer;
import com.sschudakov.utils.JTextAreaCleaner;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 20.09.2017.
 */
public class HTMLFileOpener implements FileOpener {

    private static final String END_OF_LINE = "\n";
    private Scanner scanner;

    @Override
    public void openFile(String path, JTextArea area) {

        try {
            validatePath(path);
        } catch (Exception e) {
            ExceptionRenderer.renderException(area.getRootPane(), e);
        }

        File pathFile = new File(path);

        JTextAreaCleaner.cleanJTextArea(area);

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

        try {
            this.scanner = new Scanner(pathFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        area.setText(scanner.useDelimiter("\\Z").next());
    }

    private void validatePath(String path) {

        File pathFile = new File(path);

        if(!pathFile.exists()){
            throw new IllegalArgumentException("There is no file along the path: " + path);
        }
        if(!pathFile.isFile()){
            throw new IllegalArgumentException("Path: " + path + " points to a directory");
        }
    }
}
