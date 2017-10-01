package com.sschudakov.operations;

import org.junit.Test;

import java.io.File;

/**
 * Created by Semen Chudakov on 30.09.2017.
 */
public class FileMergerTest {

    private static final String FIRST_FILE = "D:\\Workspace.java\\FirstLab\\first.txt";
    private static final String SECOND_FILE = "D:\\Workspace.java\\FirstLab\\second.txt";
    private static final String RESULTING_FILE = "D:\\Workspace.java\\FirstLab\\result.txt";

    @Test
    public void resultingFileExistsTest() {

        FileMerger.mergeFiles(FIRST_FILE, SECOND_FILE, RESULTING_FILE);

        File resultingFile = new File(RESULTING_FILE);

        if(!resultingFile.exists()){
            throw new AssertionError("resulting file does not exist");
        }
    }

}
