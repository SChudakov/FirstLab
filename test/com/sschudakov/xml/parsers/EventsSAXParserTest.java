package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsSAXParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test
    public void parseTest() {
//        Document document = DocumentReader.readDocument(EVENTS_FILE);
//        DocumentPrinter.printNode(document, "");
        EventsSAXParser parser = new EventsSAXParser();
        Events events = parser.parse(EVENTS_FILE);
        System.out.println(events);
    }
}
