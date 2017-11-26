package com.sschudakov.xml.parsers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by dbriskin on 24.04.2016.
 */
public class DocumentReader {
    /* For XML validation */
    private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";


	/**
	 * Get list of Tariff from XML file using DOM
	 *
	 * @param filename
	 *            XML filename with path
	 * @return collection of Tariff
	 */
    public static Document readDocument(String filename) {

        Document result = null;

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

			builderFactory.setNamespaceAware(true);

			// set validation
			builderFactory.setValidating(true);
			builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

			// Obtain the document
            result = documentBuilder.parse(new File(filename));

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
        return result;
    }
}
