package com.sschudakov.operations;

import java.io.File;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 02.10.2017.
 */
public class FileCreator {
    public static void createFile(String path) throws IOException {

        File pathFile = new File(path);
        if(!pathFile.exists()){
            pathFile.createNewFile();
        }else {
            throw new IllegalArgumentException("Such file already exists: " + path);
        }
    }

}
