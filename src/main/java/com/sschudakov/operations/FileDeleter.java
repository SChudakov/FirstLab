package com.sschudakov.operations;

import java.io.File;

/**
 * Created by Semen Chudakov on 02.10.2017.
 */
public class FileDeleter {
    public static void delete(String path){

        File file = new File(path);

        if (file.isFile()) {
            file.delete();
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                delete(files[i].getPath());
            }
            file.delete();
        }
    }
}
