package com.sschudakov.utils;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class FileExtensionDeterminer {

    public static boolean isHTNLFile(String path){
        return path.substring(path.length() - 5, path.length()).equals(".html");
    }
    public static boolean isTXTFile(String path){
        return path.substring(path.length() - 4, path.length()).equals(".txt");
    }
    public static boolean isExcelTableFile(String path){
        return path.substring(path.length() - 4, path.length()).equals(".ser");
    }
}
