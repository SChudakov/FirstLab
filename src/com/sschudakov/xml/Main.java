package com.sschudakov.lesson_9;


import com.sschudakov.lesson_9.bin.Tariff;
import org.w3c.dom.Document;

/**
 * Created by Semen Chudakov on 11.11.2017.
 */
public class Main {

    private static final String PATH_TO_FILE = "D:\\Workspace.java\\JavaCourse_part2\\src\\com\\sschudakov\\lesson_9\\xml_files\\tariffs.xml";

    public static void main(String[] args) {
        Document document = XMLDOMParser.parse(PATH_TO_FILE);
        for (Tariff tariff : TariffsDocumentParser.parse(document)) {
            System.out.println(tariff);
        }
    }
}
