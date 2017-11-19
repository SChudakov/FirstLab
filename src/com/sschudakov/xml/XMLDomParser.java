package com.sschudakov.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by dbriskin on 24.04.2016.
 */
public class XMLDomParser {
	/* For XML validation */
	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/**
	 * DOM document to load into
	 */
	private Document domDocument;

	/**
	 * Get list of Tariff from XML file using DOM
	 *
	 * @param filename
	 *            XML filename with path
	 * @return collection of Tariff
	 */
	public void getMailFromXmlDOMFile(String filename) {
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
			domDocument = dBuilder.parse(new File(filename));

		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Print the document from the DOM tree
		printNode(domDocument, "");

	}

	/**
	 * Recursive method to get all Nodes from DOM document
	 * 
	 * @param node
	 *            a node to start
	 * @return Tariffs collection
	 */
	private void printNode(Node node, String indent) {

		switch (node.getNodeType()) {

		case Node.DOCUMENT_NODE:
			System.out.println("<xml version=\"1.0\">\n");
			// recurse on each child
			NodeList nodes = node.getChildNodes();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					printNode(nodes.item(i), "");
				}
			}
			break;

		case Node.ELEMENT_NODE:
			String name = node.getNodeName();
			System.out.print(indent + "<" + name);
			NamedNodeMap attributes = node.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				Node current = attributes.item(i);
				System.out.print(" " + current.getNodeName() + "=\"" + current.getNodeValue() + "\"");
			}
			System.out.print(">");

			// recurse on each child
			NodeList children = node.getChildNodes();
			if (children != null) {
				for (int i = 0; i < children.getLength(); i++) {
					printNode(children.item(i), indent + "  ");
				}
			}

			System.out.print("</" + name + ">");
			break;

		case Node.TEXT_NODE:
			System.out.print(node.getNodeValue());
			break;
		}

	}

}
