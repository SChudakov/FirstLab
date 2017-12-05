package com.sschudakov;

import com.sschudakov.utils.FileExtensionDeterminer;

import java.io.*;

public class Main {

    private static final String SRC_DIRECTORY = "D:\\Workspace.java\\FirstLab\\src";

    public static void main(String[] args) {

//        GUIManager manager = new GUIManager();
//        manager.buildGUI();
        try {
            System.out.println(numOfLines(new File(SRC_DIRECTORY)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static int numOfLines(File file) throws IOException {
        if (file.isDirectory()) {
            int result = 0;
            for (File file1 : file.listFiles()) {
                result += numOfLines(file1);
            }
            return result;
        }
//        System.out.println(file.getName());
//        System.out.println(numOfLines(file));
        return FileExtensionDeterminer.isJavaFile(file.getPath()) ? countLines(file) : 0;
    }

    private static int countLines(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

//    private static int numOfLines(File file) throws IOException {
//        if (file.isDirectory()) {
//            int result = 0;
//            for (File file1 : file.listFiles()) {
//                result += countLines(file1);
//            }
//            return result;
//        }
//        if (FileExtensionDeterminer.isJavaFile(file.getPath())) {
//            return countLines(file);
//        }
//        return 0;
//    }


    private static void printCharacters() {
        for (int i = 0; i < 256; i++) {
            System.out.println(i + " " + (char) i);
        }
    }
}
