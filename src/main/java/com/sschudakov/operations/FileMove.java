package com.sschudakov.operations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Semen Chudakov on 05.10.2017.
 */
public class FileMove {
    public static boolean move(String from, String to) throws IOException {

        verifyParameters(from, to);


        File fromFile = new File(from);
//        File toFile = new File(to);

        if (fromFile.isDirectory()) {

            File newDirectory = new File(to + "\\" + fromFile.getName());

            if (!newDirectory.exists()) {
                if (!newDirectory.mkdir()) {
                    throw new RuntimeException("directory " + to + "\\" + fromFile.getName() + " failed to be created");
//                    return false;
                }
            }

            boolean allFilesAreCopiedSuccessfully = true;

            for (File file : fromFile.listFiles()) {
                if (!FileCopy.copy(file.getPath(), to + "\\" + fromFile.getName())) {
                    allFilesAreCopiedSuccessfully = false;
                }
            }

            if (allFilesAreCopiedSuccessfully) {
                FileDeleter.delete(from);
            }
        }

        if (fromFile.isFile()) {
            if (FileCopy.copy(from, to)) {
                FileDeleter.delete(from);
                return true;
            } else {
                throw new RuntimeException("file " + from + " failed to be moved to the directory + " + to);
//                return false;
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
//            if (!toFile.mkdir()) {
//                throw new IllegalArgumentException("Directory along the path to: " + to + " does not exist and failed to be created");
//            }
            throw new IllegalArgumentException("There is no destinations folder along the give path: " + to);
        }

        if (fromFile.isDirectory()) {
            Path fromPath = Paths.get(from);
            Path toPath = Paths.get(to);

            if (toPath.startsWith(fromPath)) {
                throw new IllegalArgumentException("A directory cannot be moved to its subdirectory");
            }
        }

        for (File file : toFile.listFiles()) {
            if(file.getName().equals(fromFile.getName())){
                throw new IllegalArgumentException("file or folder with name: " + fromFile.getName()
                        + " already exists in folder " + to);
            }
        }
    }
}
