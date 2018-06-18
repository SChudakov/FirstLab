package com.sschudakov.operations;

import java.io.*;
import java.nio.file.*;

/**
 * Created by Semen Chudakov on 05.10.2017.
 */
public class FileCopy {

    private static final int BUFFER_SIZE = 64 * 1024;

    public static boolean copy(String from, String to) throws IOException {

        verifyParameters(from, to);

        File fromFile = new File(from);
        File toFile = new File(to);

//        if (fromFile.isDirectory()) {
//            Files.copy(Paths.get(from), Paths.get(to));
//            for (File file : fromFile.listFiles()) {
//                Files.copy(Paths.get(file.getPath()), Paths.get(toFile.getPath() + "\\" + file.getName()),
//                        StandardCopyOption.REPLACE_EXISTING);
//            }
//        } else {
//            Files.copy(Paths.get(from), Paths.get(to), StandardCopyOption.REPLACE_EXISTING);
//        }

        if (fromFile.isDirectory()) {

            File newDirectory = new File(to + "\\" + fromFile.getName());

            if (!newDirectory.exists()) {
                if (!newDirectory.mkdir()) {
                    System.out.println("directory " + to + "\\" + fromFile.getName() + " failed to be created");
                    return false;
                }
            }


            File[] files = fromFile.listFiles();

            for (int i = 0; i < files.length; i++) {
                if (!copy(files[i].getPath(), to + "\\" + fromFile.getName())) {
                    FileDeleter.delete(toFile.getPath() + "\\" + fromFile.getName());
                    return false;
                }
            }
        }

        if (fromFile.isFile()) {
            try {

                File newFile = new File(to + "\\" + fromFile.getName());

                if (!newFile.exists()) {
                    copyFile(fromFile, newFile);
                }
            } catch (IOException e) {
                System.out.println("file " + to + "\\" + fromFile.getName() + " failed to be created");
                return false;
            }
        }

        return true;
    }

    private static void copyFile(File fromFile, File toFile) throws IOException {
//        Files.copy(Paths.get(fromFile.getPath()), Paths.get(toFile.getPath()));
        FileInputStream inputStream = new FileInputStream(fromFile);
        FileOutputStream outputStream = new FileOutputStream(toFile);

        copyFile(inputStream, outputStream);

        close(inputStream);
        close(outputStream);
    }

    private static void copyFile(InputStream inputStream, OutputStream outputStream) throws IOException {

        byte[] buffer = new byte[BUFFER_SIZE];

        int count;

        while ((count = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
    }

    private static void verifyParameters(String from, String to)  {

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
                throw new IllegalArgumentException("A directory cannot be copied to its subdirectory");
            }
        }

        for (File file : toFile.listFiles()) {
            if(file.getName().equals(fromFile.getName())){
                throw new IllegalArgumentException("file or folder with name: " + fromFile.getName()
                        + " already exists in folder " + to);
            }
        }
    }

    private static void close(FileInputStream inputStream) throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
    }

    private static void close(FileOutputStream outputStream) throws IOException {

        if (outputStream != null) {
            outputStream.flush();
            outputStream.close();
        }
    }
}
