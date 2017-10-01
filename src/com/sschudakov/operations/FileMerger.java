package com.sschudakov.operations;

import java.io.*;

/**
 * Created by Semen Chudakov on 09.09.2017.
 */
public class FileMerger {

    private static final int BUFFER_SIZE = 64 * 1024;

    public static void mergeFiles(String first, String second, String result) {

        File firstFile = new File(first);
        File secondFile = new File(second);
        File resultFile = new File(result);

        mergeFiles(firstFile, secondFile, resultFile);
    }


    private static void mergeFiles(File fromFile, File toFile, File resultFile) {

        FileInputStream firstFileInput = null;
        FileInputStream secondFileInput = null;
        FileOutputStream resultFileOutput = null;

        try {

            firstFileInput = new FileInputStream(fromFile);
            secondFileInput = new FileInputStream(toFile);
            resultFileOutput = new FileOutputStream(resultFile);

            mergeFiles(firstFileInput, resultFileOutput);
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
