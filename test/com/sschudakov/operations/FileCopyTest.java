package com.sschudakov.operations;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 07.10.2017.
 */
public class FileCopyTest {
    @Test
    public void copyFromDirToDirTest(){

        String from = "D:\\Workspace.java\\FirstLab\\test_copy\\from";
        String to = "D:\\Workspace.java\\FirstLab\\test_copy\\NEW DIR";
        try {
            FileCopy.copy(from, to);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Copy was not successful");
        }
    }

}
