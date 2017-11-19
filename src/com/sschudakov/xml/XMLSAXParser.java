package com.sschudakov.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dbriskin on 25.04.2016.
 */
public class XMLSAXParser extends DefaultHandler {

	/* Auxiliary fields to construct Tariffs */

    private List<Mail> mailList = null;

    private Mail theMail = null;
    private ArrayList<String> attachments = new ArrayList<String>();

	/* flags to mark encountered tag */

    private boolean isMailId;
    private boolean isMailFrom;
    private boolean isMailTo;
    private boolean isMailCc;
    private boolean isMailBcc;
    private boolean isMailSubject;
    private boolean isMailReceived;
    private boolean isMailBody;
    private boolean isAttachment;

    /**
     * Constructs a SAX parser
     */
    public XMLSAXParser() {
        isMailId = false;
        isMailFrom = false;
        isMailTo = false;
        isMailCc = false;
        isMailBcc = false;
        isMailSubject = false;
        isMailReceived = false;
        isMailBody = false;
        isAttachment = false;
    }

    /**
     * Gets Mail collection from file using SAX
     *
     * @param fileName XML filename to convert
     * @return Mail collection
     */
    public List<Mail> getSaxTariffs(String fileName) {
        try {
            File inputFile = new File(fileName);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File("src/maillist.xsd"));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            factory.setSchema(schema);

            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(inputFile, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.mailList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // checking node names and turn corresponding flags on
        switch (qName) {
            case "tns:maillist":
                // starting - creation of Mail collection
                mailList = new LinkedList<>();

                break;
            case "mail":
                theMail = new Mail();
                if (attributes.getValue("id") != null) {
                    theMail.setId(Long.parseLong(attributes.getValue("id")));
                }
                theMail.setReadLocal("yes".equals(attributes.getValue("isread")));

                break;
            case "mailid":
                isMailId = true;
                break;
            case "mailattach":
                attachments = new ArrayList<String>();
                break;
            case "mailfrom":
                isMailFrom = true;
                break;
            case "mailto":
                isMailTo = true;
                break;
            case "mailcc":
                isMailCc = true;
                break;
            case "mailbcc":
                isMailBcc = true;
                break;
            case "mailsubject":
                isMailSubject = true;
                break;
            case "mailreceived":
                isMailReceived = true;
                break;
            case "mailbody":
                isMailBody = true;
                break;
            case "attachment":
                isAttachment = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // adding complex objects to corresponding lists
        switch (qName) {
            case "mail":
                mailList.add(theMail);
                break;
            case "mailattach":
                theMail.setAttachmentPaths(attachments);
                break;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        // depending on flags read tags values and put them into Mail object
        if (isMailId) {
            theMail.mail_internal_number = Integer.parseInt(new String(ch, start, length));
            isMailId = false;
        } else {
            if (isMailFrom) {
                theMail.setMailFrom(new String(ch, start, length));
                isMailFrom = false;
            } else {
                if (isMailTo) {
                    theMail.setMailTo(new String(ch, start, length));
                    isMailTo = false;
                } else {
                    if (isMailCc) {
                        theMail.setMailCc(new String(ch, start, length));
                        isMailCc = false;
                    } else {
                        if (isMailBcc) {
                            theMail.setMailBcc(new String(ch, start, length));
                            isMailBcc = false;
                        } else {
                            if (isMailSubject) {
                                theMail.setMailSubject(new String(ch, start, length));
                                isMailSubject = false;
                            } else {
                                if (isMailReceived) {
                                    theMail.setMailReceived(new String(ch, start, length));
                                    isMailReceived = false;
                                } else {
                                    if (isMailBody) {
                                        theMail.setMailBody(new String(ch, start, length));
                                        isMailBody = false;
                                    } else {
                                        if (isAttachment) {
                                            attachments.add(new String(ch, start, length));
                                            isAttachment = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
