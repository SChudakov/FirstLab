package com.sschudakov.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLDomCreater {

	private Document domDocumentToWrite;

	public void createXMLDOMfile(String xmlFile, String xslFile, String htmlFile) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);

		String prefix = "http://www.kts.zu/Tariffs";

		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			domDocumentToWrite = docBuilder.newDocument();

			domDocumentToWrite.setXmlVersion("1.0");

			domDocumentToWrite.setXmlStandalone(true);

			Element rootElement = domDocumentToWrite.createElement("tns:tariff");

			rootElement.setAttribute("xmlns:tns", prefix);

			domDocumentToWrite.appendChild(rootElement);
			rootElement.setTextContent("\n");

			Element tariffname = domDocumentToWrite.createElement("name");
			tariffname.setTextContent("Super dorogoi");
			rootElement.appendChild(tariffname);

			Element operatorname = domDocumentToWrite.createElement("operatorname");
			operatorname.setTextContent("Vodafone");
			rootElement.appendChild(operatorname);

			Element payroll = domDocumentToWrite.createElement("payroll");
			payroll.setTextContent("11.0");
			rootElement.appendChild(payroll);

			Element callprices = domDocumentToWrite.createElement("callprices");
			rootElement.appendChild(callprices);

			Element priceinnet = domDocumentToWrite.createElement("price");
			priceinnet.setAttribute("name", "innet");
			callprices.appendChild(priceinnet);

			Element perminuteinnet = domDocumentToWrite.createElement("perminute");
			perminuteinnet.setTextContent("1.3");
			priceinnet.appendChild(perminuteinnet);

			Element priceoutnet = domDocumentToWrite.createElement("price");
			callprices.appendChild(priceoutnet);

			Element nameoutnet = domDocumentToWrite.createElement("name");
			nameoutnet.setTextContent("outnet");
			priceoutnet.appendChild(nameoutnet);

			Element perminuteoutnet = domDocumentToWrite.createElement("perminute");
			perminuteoutnet.setTextContent("3.45");
			priceoutnet.appendChild(perminuteoutnet);

			Element pricelandline = domDocumentToWrite.createElement("price");
			callprices.appendChild(pricelandline);

			Element namepricelandline = domDocumentToWrite.createElement("name");
			namepricelandline.setTextContent("landline");
			pricelandline.appendChild(namepricelandline);

			Element perminutelandline = domDocumentToWrite.createElement("perminute");
			perminutelandline.setTextContent("5.13");
			pricelandline.appendChild(perminutelandline);

			Element smsprice = domDocumentToWrite.createElement("smsprice");
			smsprice.setTextContent("0.4");
			rootElement.appendChild(smsprice);

			Element parameters = domDocumentToWrite.createElement("parameters");
			rootElement.appendChild(parameters);

			Element parameter1 = domDocumentToWrite.createElement("parameter");
			parameters.appendChild(parameter1);

			Element parametername1 = domDocumentToWrite.createElement("name");
			parametername1.setTextContent("friendsnumber");
			parameter1.appendChild(parametername1);

			Element parameternumber1 = domDocumentToWrite.createElement("number");
			parameternumber1.setTextContent("380501100102");
			parameter1.appendChild(parameternumber1);

			Element parameter2 = domDocumentToWrite.createElement("parameter");
			parameters.appendChild(parameter2);

			Element parametername2 = domDocumentToWrite.createElement("name");
			parametername2.setTextContent("pricing");
			parameter2.appendChild(parametername2);

			Element parameternumber2 = domDocumentToWrite.createElement("number");
			parameternumber2.setTextContent("minute");
			parameter2.appendChild(parameternumber2);

			Element parameter3 = domDocumentToWrite.createElement("parameter");
			parameters.appendChild(parameter3);

			Element parametername3 = domDocumentToWrite.createElement("name");
			parametername3.setTextContent("subscriptionfee");
			parameter3.appendChild(parametername3);

			Element parameternumber3 = domDocumentToWrite.createElement("number");
			parameternumber3.setTextContent("14");
			parameter3.appendChild(parameternumber3);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(domDocumentToWrite);
			StreamResult result = new StreamResult(new File(xmlFile));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			transformer = transformerFactory.newTransformer(new StreamSource(new File(xslFile)));

			transformer.transform(new StreamSource(new File(xmlFile)), new StreamResult(new File(htmlFile)));

			System.out.println("File saved!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (TransformerConfigurationException e) {

			e.printStackTrace();
		} catch (TransformerException e) {

			e.printStackTrace();
		}

	}
}
