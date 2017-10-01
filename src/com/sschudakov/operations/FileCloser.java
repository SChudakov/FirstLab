package com.sschudakov.operations;

import com.sschudakov.utils.JTextAreaCleaner;
import com.sschudakov.utils.OptionConfirmer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 28.09.2017.
 */
public class FileCloser {

    private File openedFile;
    private JTextArea area;

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public FileCloser(JTextArea area) {
        this.area = area;
    }

    public boolean closeFile() {
        if (openedFile != null) {

            if (hasChanges(this.openedFile, area)) {

                int confirmed = OptionConfirmer.confirmOption(this.area.getParent(), "Do you wat to save changes?");

                if(confirmed == 0){
                    FileSaver.saveFile(this.area, this.openedFile);
                    JTextAreaCleaner.cleanJTextArea(area);
                    this.openedFile = null;
                    return true;
                }
                if(confirmed == 1){
                    JTextAreaCleaner.cleanJTextArea(area);
                    this.openedFile = null;
                    return true;
                }
                if(confirmed == 2 || confirmed == -1){
                    return false;
                }
            }else{
                JTextAreaCleaner.cleanJTextArea(area);
                this.openedFile = null;
            }
        }
        return true;
    }

    private boolean hasChanges(File file, JTextArea area){
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileContent = scanner.useDelimiter("\\Z").next();

        System.out.println("file content");
        System.out.println(fileContent);
        System.out.println("area content");
        System.out.println(area.getText());
        return !fileContent.equals(area.getText());
    }
}
