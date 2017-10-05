package com.sschudakov.operations;

import java.io.File;

/**
 * Created by Semen Chudakov on 26.09.2017.
 */
public class FileRenamer {

    public static void renameFile(String path, String newName) {

        File pathFile = new File(path);
        File newNameFile = new File(changeName(pathFile, newName));


        boolean successfully = pathFile.renameTo(newNameFile);

        if (!successfully) {
            throw new RuntimeException("File failed to be renamed: make sure new name for the file is unique for this folder");
        }
    }

    private static String changeName(File file, String name) {

        String filePath = file.getPath();
        String fileName = file.getName();
        String pathWithoutName = file.getPath().substring(0, filePath.length() - fileName.length());

        return pathWithoutName + name;
    }

}
