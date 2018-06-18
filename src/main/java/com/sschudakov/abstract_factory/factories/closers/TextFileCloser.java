package com.sschudakov.abstract_factory.factories.closers;

import com.sschudakov.abstract_factory.factories.savers.TextFileSaver;
import com.sschudakov.utils.JTextAreaCleaner;
import com.sschudakov.utils.OptionConfirmer;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 28.09.2017.
 */
public class TextFileCloser implements Closer {


    private TextFileSaver saver;

    public TextFileSaver getSaver() {
        return saver;
    }

    public TextFileCloser(TextFileSaver saver) {
        this.saver = saver;
    }

    public boolean close() {
        System.out.println("has opened");
        if (hasChanges(this.saver.getFile(), this.saver.getArea())) {
            System.out.println("has changes");
            int confirmed = OptionConfirmer.confirmOption(this.saver.getArea().getParent(), "Do you wat to save changes?");

            if (confirmed == JOptionPane.YES_OPTION) {
                this.saver.save();
                JTextAreaCleaner.cleanJTextArea(this.saver.getArea());
                return true;
            }
            if (confirmed == JOptionPane.NO_OPTION) {
                JTextAreaCleaner.cleanJTextArea(this.saver.getArea());
                return true;
            }
            if (confirmed == JOptionPane.CANCEL_OPTION || confirmed == JOptionPane.CLOSED_OPTION) {
                return false;
            }
        } else {
            System.out.println("has no changes");
            JTextAreaCleaner.cleanJTextArea(this.saver.getArea());
        }

        return true;
    }

    private boolean hasChanges(File file, JTextArea area) {
        Scanner scanner = null;
        String fileContent;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (file.length() == 0) {
            fileContent = "";
        } else {
            fileContent = scanner.useDelimiter("\\Z").next();
        }
        return !fileContent.equals(area.getText());
    }
}
