package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Created by Semen Chudakov on 26.11.2017.
 */
public class EventsDOMParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test
    public void parseTest() {
        Document document = DocumentReader.readDocument(EVENTS_FILE);
//        DocumentPrinter.printNode(document, "");
        Events events = null;
        try {
            events = EventsDOMParser.parse(document);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println(events);
    }
}
