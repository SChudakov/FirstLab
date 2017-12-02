package com.sschudakov.xml.xml_view;

import com.sschudakov.xml.bin.Events;
import com.sschudakov.xml.parsers.EventsDOMParser;
import com.sschudakov.xml.parsers.EventsMarshaller;
import com.sschudakov.xml.parsers.EventsSAXParser;
import com.sschudakov.xml.parsers.EventsStAXParser;
import org.xmlpull.v1.XmlPullParserException;

import javax.swing.*;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.FileNotFoundException;

/**
 * Created by Semen Chudakov on 02.12.2017.
 */
public class XMLDocumentReader {

    private javax.swing.JRadioButton domRadioButton;
    private javax.swing.JRadioButton saxRadioButton;
    private javax.swing.JRadioButton staxRadioButton;
    private javax.swing.JRadioButton marshallingRadioButton;

    private EventsSAXParser saxParser = new EventsSAXParser();
    private EventsStAXParser stAXParser = new EventsStAXParser();


    public XMLDocumentReader(JRadioButton domRadioButton, JRadioButton saxRadioButton,
                             JRadioButton staxRadioButton, JRadioButton marshallingRadioButton) {
        this.domRadioButton = domRadioButton;
        this.saxRadioButton = saxRadioButton;
        this.staxRadioButton = staxRadioButton;
        this.marshallingRadioButton = marshallingRadioButton;
    }

    public Events read(String file) throws Exception {
        if (this.domRadioButton.isSelected()) {
            return EventsDOMParser.parse(file);
        }
        if (this.saxRadioButton.isSelected()) {
            return this.saxParser.parse(file);
        }
        if (this.staxRadioButton.isSelected()) {
            return this.stAXParser.parse(file);
        }
        if (this.marshallingRadioButton.isSelected()) {
            return EventsMarshaller.unmarshal(file);
        }
        throw new RuntimeException("no one technology for parsing is selected");
    }
}
