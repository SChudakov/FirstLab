package com.sschudakov.operations;

import java.io.File;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 05.10.2017.
 */
public class FileMove {
    public static boolean move(String from, String to) throws IOException {

        verifyParameters(from, to);


        File fromFile = new File(from);
        File toFile = new File(to);

        if (fromFile.isDirectory()) {

            File newDirectory = new File(to + "\\" + fromFile.getName());

            if (!newDirectory.exists()) {
                if (!newDirectory.mkdir()) {
                    System.out.println("directory " + to + "\\" + fromFile.getName() + " failed to be created");
                    return false;
                }
            }

            boolean allSubfilesAreCopiedSuccessfully = true;

            for (File file : fromFile.listFiles()) {
                if (!FileCopy.copy(file.getPath(), to + "\\" + fromFile.getName())) {
                    allSubfilesAreCopiedSuccessfully = false;
                }
            }

            if (allSubfilesAreCopiedSuccessfully) {
                FileDeleter.delete(from);
            }
        }

        if (fromFile.isFile()) {
            if (FileCopy.copy(from, to)) {
                FileDeleter.delete(from);
                return true;
            } else {
                System.out.println("file " + from + " failed to be moved to the directory + " + to);
                return false;
            }
        }

        return true;
    }

    private static void verifyParameters(String from, String to){

        File fromFile = new File(from);
        File toFile = new File(to);

        if (!fromFile.exists()) {
            throw new IllegalArgumentException("There is no file or directory along the given path from:  " + from);
        }

        if (toFile.exists()) {
            if (!toFile.isDirectory()) {
                throw new IllegalArgumentException("Path to: " + to + " points not to a directory");
            }
        } else {
            if (!toFile.mkdir()) {
                throw new IllegalArgumentException("Directory along the path to: " + to + " does not exist and failed to be created");
            }
        }
    }
}
