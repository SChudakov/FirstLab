package com.sschudakov.xml;

import java.io.IOException;
import java.util.HashMap;

public class XMLStAXCreater {

//	final static String NAMESPACE = "http://www.kts.zu/Tariffs";
//
//	private XmlSerializer serializer;
//	private XmlPullParserFactory factory;

	public XMLStAXCreater() {
//		try {
//			factory = XmlPullParserFactory.newInstance();
//			serializer = factory.newSerializer();
//			factory.setNamespaceAware(true);
//		} catch (XmlPullParserException e) {
//			e.printStackTrace();
//		}
	}

	public void createXMLfile(String fileName) {
//		HashMap<String, String> attrs = new HashMap<String, String>();
//
//		try {
//			// serializer.setOutput(new PrintWriter(System.out));
//			serializer.setOutput(new PrintWriter(fileName, "UTF-8"));
//
//			serializer.setPrefix("tns", NAMESPACE);
//
//			serializer.startDocument("UTF-8", true);
//			serializer.text("\n");
//
//			startTag("tariff");
//
//			writeLine("name", "Super dorogoi");
//			writeLine("operatorname", "Vodafone");
//			writeLine("payroll", "11.0");
//			startTag("callprices");
//			attrs.clear();
//			attrs.put("name", "innet");
//			startTag("price", attrs);
//			// writeLine("name", "innet");
//			writeLine("perminute", "1.3");
//			endTag("price");
//			startTag("price");
//			writeLine("name", "outnet");
//			writeLine("perminute", "3.45");
//			endTag("price");
//			startTag("price");
//			writeLine("name", "landline");
//			writeLine("perminute", "5.13");
//			endTag("price");
//			endTag("callprices");
//			writeLine("smsprice", "0.4");
//			startTag("parameters");
//			startTag("parameter");
//			writeLine("name", "friendsnumber");
//			writeLine("number", "380501100102");
//			endTag("parameter");
//			startTag("parameter");
//			writeLine("name", "pricing");
//			writeLine("number", "minute");
//			endTag("parameter");
//			startTag("parameter");
//
//			writeLine("name", "subscriptionfee");
//			writeLine("number", "14");
//
//			endTag("parameter");
//			endTag("parameters");
//
//			endTag("tariff");
//
//			serializer.endDocument();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private void startTag(String tag) throws IllegalArgumentException, IllegalStateException, IOException {
//		serializer.startTag(NAMESPACE, tag);
//		serializer.text("\n");
	}

	private void startTag(String tag, HashMap<String, String> attributes)
			throws IllegalArgumentException, IllegalStateException, IOException {
//		serializer.startTag(NAMESPACE, tag);
//		for (Map.Entry<String, String> entry : attributes.entrySet()) {
//			serializer.attribute("", entry.getKey(), entry.getValue());
//		}
//
//		serializer.text("\n");
	}

	private void endTag(String tag) throws IllegalArgumentException, IllegalStateException, IOException {
//		serializer.endTag(NAMESPACE, tag);
//		serializer.text("\n");
	}

	private void writeLine(String tag, String line) throws IOException {
//		serializer.startTag(NAMESPACE, tag);
//		serializer.text(line);
//		serializer.endTag(NAMESPACE, tag);
//		serializer.text("\n");
	}

}
