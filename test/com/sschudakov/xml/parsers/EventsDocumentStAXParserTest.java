package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsDocumentStAXParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test(timeout = 1000)
    public void parseTest() {
        EventsDocumentStAxParser parser = new EventsDocumentStAxParser(EVENTS_FILE);
        try {
            Events events = parser.parse();
            System.out.println(events);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
