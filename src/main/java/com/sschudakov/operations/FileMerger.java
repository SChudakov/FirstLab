package com.sschudakov.operations;

import com.sschudakov.utils.FileExtensionDeterminer;

import java.io.*;

/**
 * Created by Semen Chudakov on 09.09.2017.
 */
public class FileMerger {

    private static final int BUFFER_SIZE = 64 * 1024;

    public static void mergeFiles(String first, String second, String result) {
        validateParameters(first, second, result);

        File firstFile = new File(first);
        File secondFile = new File(second);
        File resultFile = new File(result);

        mergeFiles(firstFile, secondFile, resultFile);
    }

    private static void mergeFiles(File firstFile, File secondFile, File resultFile) {

        FileInputStream firstFileInput = null;
        FileInputStream secondFileInput = null;
        FileOutputStream resultFileOutput = null;

        try {

            firstFileInput = new FileInputStream(firstFile);
            secondFileInput = new FileInputStream(secondFile);
            resultFileOutput = new FileOutputStream(resultFile);

            mergeFiles(firstFileInput, resultFileOutput);
            if (FileExtensionDeterminer.isHTNLFile(firstFile.getPath()) || FileExtensionDeterminer.isTXTFile(secondFile.getPath())
                    && FileExtensionDeterminer.isHTNLFile(secondFile.getPath()) || FileExtensionDeterminer.isTXTFile(secondFile.getPath())) {
                resultFileOutput.write(new byte[]{'\n'});
            }
            mergeFiles(secondFileInput, resultFileOutput);

            close(firstFileInput);
            close(secondFileInput);

            close(resultFileOutput);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mergeFiles(InputStream inputStream, OutputStream outputStream) throws IOException {

        byte[] buffer = new byte[BUFFER_SIZE];

        int count;

        while ((count = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
    }

    private static void validateParameters(String first, String second, String result) {

        File firstFile = new File(first);
        File secondFile = new File(second);
        File resultFile = new File(result);

        if (!firstFile.exists() || !firstFile.isFile()) {
            throw new IllegalArgumentException("There is no file along the given path:  " + first);
        }
        if (!secondFile.exists() || !secondFile.isFile()) {
            throw new IllegalArgumentException("There is no file along the given path:  " + second);
        }

//        if (resultFile.exists()) {
//            throw new IllegalArgumentException("Selected files cannot be merged to already existing file: " + result);
//        }

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
