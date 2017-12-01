package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.bin.LastFirstMiddleName;
import com.sschudakov.xml.bin.Time;
import com.sschudakov.xml.utils.TagNames;
import com.sschudakov.xml.utils.XMLDateParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class EventsDOMParser {


    public static Events parse(Document document) throws DatatypeConfigurationException {

        NodeList nodeList = document.getChildNodes();
        Node events = nodeList.item(0);

        return parseEvents(events);
    }

    private static Events parseEvents(Node events) throws DatatypeConfigurationException {
        if (!events.getNodeName().equals(TagNames.TNS_EVENTS)) {
            throw new IllegalArgumentException("node " + events + " is not a " + TagNames.TNS_EVENTS + " node");
        }

        List<Event> list = new ArrayList<>();
        NodeList eventsNodes = events.getChildNodes();

        for (int i = 0; i < eventsNodes.getLength(); i++) {
            if (i % 2 == 1) {
                if (eventsNodes.item(i).getNodeName().equals(TagNames.EVENT)) {
                    list.add(parseEvent(eventsNodes.item(i)));
                }
            }
        }
        return new Events(list);
    }

    private static Event parseEvent(Node event) throws DatatypeConfigurationException {
        if (!event.getNodeName().equals(TagNames.EVENT)) {
            throw new IllegalArgumentException("node " + event + " is not a" + TagNames.EVENT + " node");
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
        if (!lastFirstMiddleName.getNodeName().equals(TagNames.LAST_FIRST_MIDDLE_NAME)) {
            throw new IllegalArgumentException("node " + lastFirstMiddleName + " is not a " + TagNames.LAST_FIRST_MIDDLE_NAME + " node");
        }

        NodeList list = lastFirstMiddleName.getChildNodes();
        return new LastFirstMiddleName(
                parseFirstName(list.item(1)),
                parseMiddleName(list.item(3)),
                parseLastName(list.item(5))
        );
    }

    private static String parseFirstName(Node firstName) {
        if (!firstName.getNodeName().equals(TagNames.FIRST_NAME)) {
            throw new IllegalArgumentException("node " + firstName + " is not a" + TagNames.FIRST_NAME + " node");
        }
        return firstName.getTextContent();
    }

    private static String parseMiddleName(Node middleName) {
        if (!middleName.getNodeName().equals(TagNames.MIDDLE_NAME)) {
            throw new IllegalArgumentException("node " + middleName + " is not a" + TagNames.MIDDLE_NAME + " node");
        }
        return middleName.getTextContent();
    }

    private static String parseLastName(Node lastName) {
        if (!lastName.getNodeName().equals(TagNames.LAST_NAME)) {
            throw new IllegalArgumentException("node " + lastName + " is not a" + TagNames.LAST_NAME + " node");
        }
        return lastName.getTextContent();
    }


    private static String parseFaculty(Node faculty) {
        if (!faculty.getNodeName().equals(TagNames.FACULTY)) {
            throw new IllegalArgumentException("node " + faculty + " is not a" + TagNames.FACULTY + " node");
        }
        return faculty.getTextContent();
    }

    private static String parseSubFaculty(Node subFaculty) {
        if (!subFaculty.getNodeName().equals(TagNames.SUB_FACULTY)) {
            throw new IllegalArgumentException("node " + subFaculty + " is not a" + TagNames.SUB_FACULTY + " node");
        }
        return subFaculty.getTextContent();
    }

    private static String parseBranchOfStudy(Node branchOfStudy) {
        if (!branchOfStudy.getNodeName().equals(TagNames.BRANCH_OF_STUDY)) {
            throw new IllegalArgumentException("node " + branchOfStudy + " is not a " + TagNames.BRANCH_OF_STUDY + " node");
        }
        return branchOfStudy.getTextContent();
    }


    private static Time parseTime(Node time) throws DatatypeConfigurationException {
        if (!time.getNodeName().equals(TagNames.TIME)) {
            throw new IllegalArgumentException("node " + time + " is not a " + TagNames.TIME + " node");
        }
        NodeList list = time.getChildNodes();
        return new Time(
                parseBegin(list.item(1)),
                parseEnds(list.item(3))
        );
    }

    private static XMLGregorianCalendar parseBegin(Node begin) throws DatatypeConfigurationException {
        if (!begin.getNodeName().equals(TagNames.BEGIN)) {
            throw new IllegalArgumentException("node " + begin + " is not a " + TagNames.BEGIN + " node");
        }
        return XMLDateParser.parse(begin.getTextContent());
    }

    private static XMLGregorianCalendar parseEnds(Node end) throws DatatypeConfigurationException {

        if (!end.getNodeName().equals(TagNames.END)) {
            throw new IllegalArgumentException("node " + end + " is not a " + TagNames.END + " node");
        }
        return XMLDateParser.parse(end.getTextContent());
    }
}
