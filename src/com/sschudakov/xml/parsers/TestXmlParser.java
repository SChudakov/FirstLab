package com.sschudakov.xml.parsers;

import com.sschudakov.xml.Mail;
import com.sschudakov.xml.XMLDomCreater;
import com.sschudakov.xml.XMLSAXParser;
import com.sschudakov.xml.XMLStAXCreater;

import java.util.List;

public class TestXmlParser {

    public static void test() {

        // ------------ Parsers -----------//
        // Load tariffs - StAX
        XMLStAXParser xmlStAXParser = new XMLStAXParser("src\\mails.xml");
        List<Mail> mailList = xmlStAXParser.getTariffsFromXmlFile();

        System.out.println(mailList);

        // Load tariffs - DOM
        XMLDomParser xmlDomParser = new XMLDomParser();
        xmlDomParser.getMailFromXmlDOMFile("src/mails.xml");

        // Load tariffs - SAX
        XMLSAXParser xmlsaxParser = new XMLSAXParser();
        List<Mail> mailList2 = xmlsaxParser.getSaxTariffs("src\\mails.xml");
        System.out.println(mailList2);

        // -------------Creaters ---------//
        XMLDomCreater domCreater = new XMLDomCreater();
        domCreater.createXMLDOMfile("tariffsDOM.xml", "tariffs.xsl", "tariffsDOM.html");

        XMLStAXCreater stAXCreater = new XMLStAXCreater();
        stAXCreater.createXMLfile("tariffs.xml");

    }

}
