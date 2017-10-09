package com.sschudakov.operations;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Semen Chudakov on 09.10.2017.
 */
public class FileMoveTest {
    @Test
    public void copyFromDirToDirTest(){

        String from = "D:\\Workspace.java\\FirstLab\\test_copy\\to\\from";
        String to = "D:\\Workspace.java\\FirstLab\\test_copy";
        try {
            FileMove.move(from, to);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Copy was not successful");
        }
    }
}
