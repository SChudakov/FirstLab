package com.sschudakov.xml.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class DateParser {

    private static final String DATE_PATTERN = "yyyy-MM-ddTHH:mm:ss";

    public static XMLGregorianCalendar parse(String date) throws DatatypeConfigurationException {
        LocalDateTime parsedLocalDateTime = parseLocalDateTime(date);
        return localDateTimeToXMLGC(parsedLocalDateTime);
    }

    public static LocalDateTime parseLocalDateTime(String date) {
        return LocalDateTime.parse(date);
    }

    public static LocalDateTime XMLGCToLocalDateTime(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }

    public static XMLGregorianCalendar localDateTimeToXMLGC(LocalDateTime localDateTime) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDateTime.atZone(ZoneId.systemDefault()));
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }
}
