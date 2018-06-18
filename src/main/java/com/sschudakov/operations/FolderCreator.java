package com.sschudakov.operations;

import java.io.File;
import java.io.IOException;

/**
 * Created by Semen Chudakov on 04.10.2017.
 */
public class FolderCreator {
    public static void createFolder(String path) throws IOException {

        File folder = new File(path);

        if(!folder.exists()){
            folder.mkdir();
        }else {
            throw new IllegalArgumentException("Such file or folder already exists: " + path);
        }
    }
}
