package com.sschudakov.operations;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class FileMergerTest {

    private static final String FIRST_FILE = "D:\\Workspace.java\\FirstLab\\test\\first.txt";
    private static final String SECOND_FILE = "D:\\Workspace.java\\FirstLab\\test\\second.txt";
    private static final String RESULTING_FILE = "D:\\Workspace.java\\FirstLab\\test\\result.txt";
    private static final String NOT_EXISTING_FILE = "D:\\Workspace.java\\FirstLab\\test\\blabla.txt";

    @Test
    public void resultingFileExistsTest() {

        FileMerger.mergeFiles(FIRST_FILE, SECOND_FILE, RESULTING_FILE);

        File resultingFile = new File(RESULTING_FILE);

        if(!resultingFile.exists()){
            throw new AssertionError("resulting file does not exist");
        }
    }

    @Test
    public void resultingFileContentMatchingText() {

        Scanner firstFileScanner = null;
        Scanner secondFileScanner = null;
        Scanner resultingFileScanner = null;
        try {
            FileMerger.mergeFiles(FIRST_FILE, SECOND_FILE, RESULTING_FILE);

            firstFileScanner = new Scanner(new File(FIRST_FILE));
            secondFileScanner = new Scanner(new File(SECOND_FILE));
            resultingFileScanner = new Scanner(new File(RESULTING_FILE));

            String firstFileContent = firstFileScanner.useDelimiter("\\Z").next();
            String secondFileContent = secondFileScanner.useDelimiter("\\Z").next();
            String resultingFileContent = resultingFileScanner.useDelimiter("\\Z").next();

            if (!(firstFileContent + secondFileContent).equals(resultingFileContent)) {
                throw new AssertionError("Content of the resulting file does not match to contents of first and second files");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mergingFileWithItselfEnabledText() {

        try {
            FileMerger.mergeFiles(FIRST_FILE, FIRST_FILE, RESULTING_FILE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Merging of file with itself has not been performed successfully");
        }
    }

    @Test
    public void mergingWithNotExistingFileText() {

        try {
            FileMerger.mergeFiles(FIRST_FILE, NOT_EXISTING_FILE, RESULTING_FILE);
            throw new AssertionError("No exception while merging with not existing file");
        } catch (IllegalArgumentException ignore) {
            //NOP
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Received not IllegalArgumentException");
        }
    }
}
