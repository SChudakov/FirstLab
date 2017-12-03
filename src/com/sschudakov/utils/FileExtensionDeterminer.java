package com.sschudakov.utils;

/**
 * Created by Semen Chudakov on 03.10.2017.
 */
public class FileExtensionDeterminer {

    private static final String HTML_EXTENSION = ".html";
    private static final String TXT_EXTENSION = ".txt";
    private static final String SERIALIZED_FILE_EXTENSION = ".ser";
    private static final String XSL_EXTENSION = ".xsl";
    private static final String XML_EXTENSION = ".xml";

    public static boolean isTextFile(String path) {
        return isHTNLFile(path) || isTXTFile(path);
    }

    public static boolean isHTNLFile(String path){
        return path.endsWith(HTML_EXTENSION);
    }
    public static boolean isTXTFile(String path){
        return path.endsWith(TXT_EXTENSION);
    }

    public static boolean isTableFile(String path){
        return path.endsWith(SERIALIZED_FILE_EXTENSION);
    }

    public static boolean isXML(String path) {
        return path.endsWith(XML_EXTENSION);
    }

    public static boolean isXSL(String path) {
        return path.endsWith(XSL_EXTENSION);
    }
}
