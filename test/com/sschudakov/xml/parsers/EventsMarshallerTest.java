package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Events;
import org.junit.Test;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsMarshallerTest {
    private static String EVENTS_FILE = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xml";

    @Test
    public void unmarshalTest() {
        try {
            Events events = EventsMarshaller.unmarshal(EVENTS_FILE);
            System.out.println(events);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
