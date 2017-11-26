package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.bin.LastFirstMiddleName;
import com.sschudakov.xml.bin.Time;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class EventsDocumentDOMParser {

    private static final String EVENTS = "tns:events";
    private static final String EVENT = "event";

    private static final String LAST_FIRST_MIDDLE_NAME = "last_first_middle_name";

    private static final String FIRST_NAME = "first_name";
    private static final String MIDDLE_NAME = "middle_name";
    private static final String LAST_NAME = "last_name";

    private static final String FACULTY = "faculty";
    private static final String SUB_FACULTY = "sub-faculty";
    private static final String BRANCH_OF_STUDY = "branch-of-study";

    private static final String TIME = "time";

    private static final String BEGIN = "begin";
    private static final String END = "end";


    public static Events parse(Document document) {

        NodeList nodeList = document.getChildNodes();
        Node events = nodeList.item(0);

        return parseEvents(events);
    }

    private static Events parseEvents(Node events) {
        if (!events.getNodeName().equals(EVENTS)) {
            throw new IllegalArgumentException("node " + events + " is not a " + EVENTS + " node");
        }

        List<Event> list = new ArrayList<>();
        NodeList eventsNodes = events.getChildNodes();

        for (int i = 0; i < eventsNodes.getLength(); i++) {
            if (i % 2 == 1) {
                if (eventsNodes.item(i).getNodeName().equals(EVENT)) {
                    list.add(parseEvent(eventsNodes.item(i)));
                }
            }
        }
        return new Events(list);
    }

    private static Event parseEvent(Node event) {
        if (!event.getNodeName().equals(EVENT)) {
            throw new IllegalArgumentException("node " + event + " is not a" + EVENT + " node");
        }
        NodeList tariffNodes = event.getChildNodes();


        Event result = new Event(
                parseLastFirstMiddleName(tariffNodes.item(1)),
                parseFaculty(tariffNodes.item(3)),
                parseSubFaculty(tariffNodes.item(5)),
                parseBranchOfStudy(tariffNodes.item(7)),
                parseTime(tariffNodes.item(9))
        );
//        }
        return result;
    }

    private static LastFirstMiddleName parseLastFirstMiddleName(Node lastFirstMiddleName) {
        if (!lastFirstMiddleName.getNodeName().equals(LAST_FIRST_MIDDLE_NAME)) {
            throw new IllegalArgumentException("node " + lastFirstMiddleName + " is not a " + LAST_FIRST_MIDDLE_NAME + " node");
        }

        NodeList list = lastFirstMiddleName.getChildNodes();
        return new LastFirstMiddleName(
                parseFirstName(list.item(1)),
                parseMiddleName(list.item(3)),
                parseLastName(list.item(5))
        );
    }

    private static String parseFirstName(Node firstName) {
        if (!firstName.getNodeName().equals(FIRST_NAME)) {
            throw new IllegalArgumentException("node " + firstName + " is not a" + FIRST_NAME + " node");
        }
        return firstName.getTextContent();
    }

    private static String parseMiddleName(Node middleName) {
        if (!middleName.getNodeName().equals(MIDDLE_NAME)) {
            throw new IllegalArgumentException("node " + middleName + " is not a" + MIDDLE_NAME + " node");
        }
        return middleName.getTextContent();
    }

    private static String parseLastName(Node lastName) {
        if (!lastName.getNodeName().equals(LAST_NAME)) {
            throw new IllegalArgumentException("node " + lastName + " is not a" + LAST_NAME + " node");
        }
        return lastName.getTextContent();
    }


    private static String parseFaculty(Node faculty) {
        if (!faculty.getNodeName().equals(FACULTY)) {
            throw new IllegalArgumentException("node " + faculty + " is not a" + FACULTY + " node");
        }
        return faculty.getTextContent();
    }

    private static String parseSubFaculty(Node subFaculty) {
        if (!subFaculty.getNodeName().equals(SUB_FACULTY)) {
            throw new IllegalArgumentException("node " + subFaculty + " is not a" + SUB_FACULTY + " node");
        }
        return subFaculty.getTextContent();
    }

    private static String parseBranchOfStudy(Node branchOfStudy) {
        if (!branchOfStudy.getNodeName().equals(BRANCH_OF_STUDY)) {
            throw new IllegalArgumentException("node " + branchOfStudy + " is not a " + BRANCH_OF_STUDY + " node");
        }
        return branchOfStudy.getTextContent();
    }


    private static Time parseTime(Node time) {
        if (!time.getNodeName().equals(TIME)) {
            throw new IllegalArgumentException("node " + time + " is not a " + TIME + " node");
        }
        NodeList list = time.getChildNodes();
        return new Time(
                parseBegin(list.item(1)),
                parseEnds(list.item(3))
        );
    }

    private static XMLGregorianCalendar parseBegin(Node begin) {
//        NodeList prices = begin.getChildNodes();
//        Parameters result = new Parameters(
//                parseEnds(prices.item(0)),
//                parseBilling(prices.item(1)),
//                parseInvolvementPrice(prices.item(2))
//        );


        return null;
    }

    private static XMLGregorianCalendar parseEnds(Node end) {

        return null;
    }
}
