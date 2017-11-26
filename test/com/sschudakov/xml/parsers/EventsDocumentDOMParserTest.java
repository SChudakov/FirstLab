package com.sschudakov.xml.parsers;

import com.sschudakov.xml.DocumentPrinter;
import com.sschudakov.xml.bin.Events;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * Created by Semen Chudakov on 26.11.2017.
 */
public class EventsDocumentDOMParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test
    public void parseTest() {
        Document document = DocumentReader.readDocument(EVENTS_FILE);
//        DocumentPrinter.printNode(document, "");
        Events events = EventsDocumentDOMParser.parse(document);
        System.out.println(events);
    }
}
