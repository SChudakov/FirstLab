package com.sschudakov.xml.parsers;

import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Semen Chudakov on 30.11.2017.
 */
public class EventsMarshaller {

    private static final String PACKAGE_NAME = "com.sschudakov.xml.bin";
    private static final String STARTING_TAG = "\"<?xml version='1.0' encoding='UTF-8' \tstandalone='yes'?>\"";

    private static JAXBContext jaxbContext;

    static {
        try {
            jaxbContext = JAXBContext.newInstance(PACKAGE_NAME);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void marshal(Events events, String outputFilename) throws Exception {
        Marshaller marshaller = jaxbContext.createMarshaller();
        FileOutputStream outputStream = new FileOutputStream(outputFilename);

        outputStream.write(STARTING_TAG.getBytes());
//        str = "<?xml-stylesheet type='text/xsl href='subway.xsl' ?>";
//        outputStream.write(str.getBytes());

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        marshaller.marshal(events, outputStream);
    }

    public static Events unmarshal(String inputFileName) throws Exception {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Events) unmarshaller.unmarshal(new FileInputStream(inputFileName));
    }
}
