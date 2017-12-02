package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileNotFoundException;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsStAXParserTest {

    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test(timeout = 1000)
    public void parseTest() {
        EventsStAXParser parser = new EventsStAXParser();
        try {
            Events events = parser.parse(EVENTS_FILE);
            System.out.println(events);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }
}
