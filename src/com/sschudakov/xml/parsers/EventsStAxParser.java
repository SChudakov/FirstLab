package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.bin.LastFirstMiddleName;
import com.sschudakov.xml.bin.Time;
import com.sschudakov.xml.utils.TagNames;
import com.sschudakov.xml.utils.XMLDateParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbriskin on 24.04.2016.
 */
public class EventsStAxParser {

    private XmlPullParser xmlPullParser;

    /**
     * Constructs XML StAX Parser
     *
     * @param fileName filename to parse
     */
    public EventsStAxParser(String fileName) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            this.xmlPullParser = factory.newPullParser();
            this.xmlPullParser.setInput(new FileReader(fileName));
        } catch (XmlPullParserException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Mail collection from XML file using StAX
     *
     * @return Mail collection
     */
    public Events parse() throws DatatypeConfigurationException {

        String tagName;
        int tagDepth;

        Events events = null;
        List<Event> eventList = null;
        Event event = null;
        LastFirstMiddleName lastFirstMiddleName = null;
        Time time = null;

        try {
            while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {
                tagName = xmlPullParser.getName();
                if (this.xmlPullParser.getEventType() == XmlPullParser.START_TAG) {
                    if (tagName.equals(TagNames.EVENTS)) {
                        events = new Events();
                        eventList = new ArrayList<>();
                    }
                    if (tagName.equals(TagNames.EVENT)) {
                        event = new Event();
                    }
                    if (tagName.equals(TagNames.LAST_FIRST_MIDDLE_NAME)) {
                        lastFirstMiddleName = new LastFirstMiddleName();
                    }
                    if (tagName.equals(TagNames.FIRST_NAME)) {
                        this.xmlPullParser.next();
                        if (this.xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            lastFirstMiddleName.setFirstName(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.MIDDLE_NAME)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            lastFirstMiddleName.setMiddleName(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.LAST_NAME)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            lastFirstMiddleName.setLastName(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.FACULTY)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            event.setFaculty(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.SUB_FACULTY)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            event.setSubFaculty(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.BRANCH_OF_STUDY)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            event.setBranchOfStudy(this.xmlPullParser.getText());
                        }
                    }
                    if (tagName.equals(TagNames.TIME)) {
                        time = new Time();
                    }
                    if (tagName.equals(TagNames.BEGIN)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            time.setBegin(XMLDateParser.parse(this.xmlPullParser.getText()));
                        }
                    }
                    if (tagName.equals(TagNames.END)) {
                        this.xmlPullParser.next();
                        if (xmlPullParser.getEventType() == XmlPullParser.TEXT) {
                            time.setEnd(XMLDateParser.parse(this.xmlPullParser.getText()));
                        }
                    }
                }

                if (this.xmlPullParser.getEventType() == XmlPullParser.END_TAG) {
                    if (tagName.equals(TagNames.EVENTS)) {
                        events.setEvent(eventList);
                        eventList = null;
                    }
                    if (tagName.equals(TagNames.EVENT)) {

                        eventList.add(event);
                        event = null;
                    }
                    if (tagName.equals(TagNames.LAST_FIRST_MIDDLE_NAME)) {
                        event.setLastFirstMiddleName(lastFirstMiddleName);
                        lastFirstMiddleName = null;
                    }
                    if (tagName.equals(TagNames.FIRST_NAME)) {

                    }
                    if (tagName.equals(TagNames.MIDDLE_NAME)) {

                    }
                    if (tagName.equals(TagNames.LAST_NAME)) {

                    }
                    if (tagName.equals(TagNames.FACULTY)) {

                    }
                    if (tagName.equals(TagNames.SUB_FACULTY)) {

                    }
                    if (tagName.equals(TagNames.BRANCH_OF_STUDY)) {

                    }
                    if (tagName.equals(TagNames.TIME)) {
                        event.setTime(time);
                        time = null;
                    }
                    if (tagName.equals(TagNames.BEGIN)) {

                    }
                    if (tagName.equals(TagNames.END)) {

                    }
                }

                this.xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }
}
