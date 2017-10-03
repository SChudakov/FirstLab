package com.sschudakov.operations;

import java.io.File;

/**
 * Created by Semen Chudakov on 02.10.2017.
 */
public class FileDeleter {
    public static void deleteFile(String path){
        validatePath(path);
        File pathFile = new File(path);
        pathFile.delete();
    }

    private static void validatePath(String path){

        File pathFile = new File(path);

        if(!pathFile.isFile()){
            throw new IllegalArgumentException("There is no file along the path: " + path);
        }
    }
}
