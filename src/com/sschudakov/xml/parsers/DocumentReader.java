package com.sschudakov.xml.parsers;

import com.sschudakov.xml.MyErrorHandler;
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
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			factory.setNamespaceAware(true);

			// set validation
			factory.setValidating(true);
			factory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

			DocumentBuilder dBuilder = factory.newDocumentBuilder();

			// error handler
			dBuilder.setErrorHandler(new MyErrorHandler("dom_parsing.log"));

			// Obtain the document
            result = dBuilder.parse(new File(filename));

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
        return result;
    }
}
