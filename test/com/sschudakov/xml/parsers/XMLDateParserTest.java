package com.sschudakov.xml.parsers;

import com.sschudakov.xml.utils.DateParser;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;

/**
 * Created by Semen Chudakov on 01.12.2017.
 */
public class XMLDateParserTest {

    private static final String TIME = "2001-10-26T21:32:52";

    @Test
    public void parseTest(){
        try {
            System.out.println(DateParser.parse(TIME));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
