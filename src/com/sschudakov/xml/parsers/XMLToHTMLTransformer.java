package com.sschudakov.xml.parsers;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by Semen Chudakov on 01.12.2017.
 */
public class XMLToHTMLTransformer {

    public static void transform(String xmlFile, String xslFile, String htmlFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(xslFile)));
        transformer.transform(new StreamSource(new File(xmlFile)),
                new StreamResult(new File(htmlFile)));
    }
}
