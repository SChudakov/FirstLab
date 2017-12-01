package com.sschudakov.xml.parsers;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class XMLDateParser {

    private static final String DATE_PATTERN = "yyyy-MM-ddTHH:mm:ss";

    public static XMLGregorianCalendar parse(String date) throws DatatypeConfigurationException {
        LocalDateTime parseLocalDateTime = parseLocalDateTime(date);
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(parseLocalDateTime.atZone(ZoneId.systemDefault()));

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
    private static LocalDateTime parseLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }
}
