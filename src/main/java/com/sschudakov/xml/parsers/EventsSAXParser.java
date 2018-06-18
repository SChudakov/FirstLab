package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.bin.LastFirstMiddleName;
import com.sschudakov.xml.bin.Time;
import com.sschudakov.xml.utils.TagNames;
import com.sschudakov.xml.utils.DateParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbriskin on 25.04.2016.
 */
public class EventsSAXParser extends DefaultHandler {

    private static final String PATH_TO_SCHEMA = "D:\\Workspace.java\\FirstLab\\xml\\student_government_events.xsd";

    private Events events;
    private List<Event> eventList;
    private Event event;
    private LastFirstMiddleName lastFirstMiddleName;
    private Time time;

    private boolean isEvents;
    private boolean isEvent;
    private boolean isLastFirstMiddleName;
    private boolean isFirstName;
    private boolean isMiddleName;
    private boolean isLastName;
    private boolean isFaculty;
    private boolean isSubFaculty;
    private boolean isBranchOfStudy;
    private boolean isTime;
    private boolean isBegin;
    private boolean isEnd;


    /**
     * Constructs a SAX parser
     */

    /**
     * Gets Mail collection from file using SAX
     *
     * @param fileName XML filename to convert
     * @return Mail collection
     */
    public Events parse(String fileName) {
        try {
            File inputFile = new File(fileName);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(PATH_TO_SCHEMA));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setSchema(schema);

            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.events;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals(TagNames.TNS_EVENTS)) {
            this.events = new Events();
            this.eventList = new ArrayList<>();
            this.isEvents = true;
        }
        if (qName.equals(TagNames.EVENT)) {
            this.event = new Event();
            this.isEvent = true;
        }
        if (qName.equals(TagNames.LAST_FIRST_MIDDLE_NAME)) {
            this.lastFirstMiddleName = new LastFirstMiddleName();
            this.isLastFirstMiddleName = true;
        }
        if (qName.equals(TagNames.FIRST_NAME)) {
            this.isFirstName = true;
        }
        if (qName.equals(TagNames.MIDDLE_NAME)) {
            this.isMiddleName = true;
        }
        if (qName.equals(TagNames.LAST_NAME)) {
            this.isLastName = true;
        }
        if (qName.equals(TagNames.FACULTY)) {
            this.isFaculty = true;
        }
        if (qName.equals(TagNames.SUB_FACULTY)) {
            this.isSubFaculty = true;
        }
        if (qName.equals(TagNames.BRANCH_OF_STUDY)) {
            this.isBranchOfStudy = true;
        }
        if (qName.equals(TagNames.TIME)) {
            this.time = new Time();
            this.isTime = true;
        }
        if (qName.equals(TagNames.BEGIN)) {
            this.isBegin = true;
        }
        if (qName.equals(TagNames.END)) {
            this.isEnd = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(TagNames.TNS_EVENTS)) {
            this.events.setEvent(this.eventList);
            this.eventList = null;
            this.isEvents = false;
        }
        if (qName.equals(TagNames.EVENT)) {
            this.eventList.add(this.event);
            this.event = null;
            this.isEvent = false;
        }
        if (qName.equals(TagNames.LAST_FIRST_MIDDLE_NAME)) {
            this.event.setLastFirstMiddleName(this.lastFirstMiddleName);
            this.lastFirstMiddleName = null;
            this.isLastFirstMiddleName = false;
        }
        if (qName.equals(TagNames.FIRST_NAME)) {
            this.isFirstName = false;
        }
        if (qName.equals(TagNames.MIDDLE_NAME)) {
            this.isMiddleName = false;
        }
        if (qName.equals(TagNames.LAST_NAME)) {
            this.isLastName = false;
        }
        if (qName.equals(TagNames.FACULTY)) {
            this.isFaculty = false;
        }
        if (qName.equals(TagNames.SUB_FACULTY)) {
            this.isSubFaculty = false;
        }
        if (qName.equals(TagNames.BRANCH_OF_STUDY)) {
            this.isBranchOfStudy = false;
        }
        if (qName.equals(TagNames.TIME)) {
            this.event.setTime(this.time);
            this.time = null;
            this.isTime = false;
        }
        if (qName.equals(TagNames.BEGIN)) {
            this.isBegin = false;
        }
        if (qName.equals(TagNames.END)) {
            this.isEnd = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        // depending on flags read tags values and put them into Mail object
        if (this.isFirstName) {
            this.lastFirstMiddleName.setFirstName(new String(ch, start, length));
        } else {
            if (this.isMiddleName) {
                this.lastFirstMiddleName.setMiddleName(new String(ch, start, length));
            } else {
                if (this.isLastName) {
                    this.lastFirstMiddleName.setLastName(new String(ch, start, length));
                } else {
                    if (this.isFaculty) {
                        this.event.setFaculty(new String(ch, start, length));
                    } else {
                        if (this.isSubFaculty) {
                            this.event.setSubFaculty(new String(ch, start, length));
                        } else {
                            if (this.isBranchOfStudy) {
                                this.event.setBranchOfStudy(new String(ch, start, length));
                            } else {
                                if (this.isBegin) {
                                    try {
                                        this.time.setBegin(DateParser.parse(new String(ch, start, length)));
                                    } catch (DatatypeConfigurationException e) {
                                        e.printStackTrace();
                                        throw new RuntimeException(e);
                                    }
                                } else {
                                    if (this.isEnd) {
                                        try {
                                            this.time.setEnd(DateParser.parse(new String(ch, start, length)));
                                        } catch (DatatypeConfigurationException e) {
                                            e.printStackTrace();
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
