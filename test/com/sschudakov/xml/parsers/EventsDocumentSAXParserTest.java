package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;
import org.w3c.dom.Document;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsDocumentSAXParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test
    public void parseTest() {
//        Document document = DocumentReader.readDocument(EVENTS_FILE);
//        DocumentPrinter.printNode(document, "");
        EventsDocumentSAXParser parser = new EventsDocumentSAXParser();
        Events events = parser.parse(EVENTS_FILE);
        System.out.println(events);
    }
}
