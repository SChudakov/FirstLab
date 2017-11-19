package com.sschudakov.xml;

import java.util.List;

/**
 * Created by dbriskin on 24.04.2016.
 */
public class XMLStAXParser {

//    private XmlPullParser xmlParser;

    /**
     * Constructs XML StAX Parser
     *
     * @param fileName filename to parse
     */
    public XMLStAXParser(String fileName) {
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            factory.setNamespaceAware(true);
//            xmlParser = factory.newPullParser();
//            xmlParser.setInput(new FileReader(fileName));
//        } catch (XmlPullParserException | FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Get Mail collection from XML file using StAX
     *
     * @return Mail collection
     */
    public List<Mail> getTariffsFromXmlFile() {

//        String tagName;
//        int tagDepth;
//
//        List<Mail> mailList = null;
//
//        Mail theMail = null;
//        ArrayList<String> attachments = new ArrayList<String>();
//
//        try {
//            while (xmlParser.getEventType() != XmlPullParser.END_DOCUMENT) {
//                tagName = xmlParser.getName();
//                tagDepth = xmlParser.getDepth();
//
//                switch (xmlParser.getEventType()) {
//
//                    // start tag
//                    // checking node names and do corresponding routines
//                    case XmlPullParser.START_TAG:
//                        if (tagName.equals("maillist")) {
//                            // starting - creation of Mail collection
//                            mailList = new LinkedList<>();
//                            break;
//                        }
//                        if (tagName.equals("mail")) {
//                            theMail = new Mail();
//                            for (int i = 0; i < xmlParser.getAttributeCount(); i++) {
//                                if (xmlParser.getAttributeName(i).equals("id")) {
//                                    theMail.id = Long.parseLong(xmlParser.getAttributeValue(i));
//                                }
//                                if (xmlParser.getAttributeName(i).equals("isread")) {
//                                    theMail.setReadLocal(xmlParser.getAttributeValue(i).equals("yes"));
//                                }
//                            }
//                        }
//                        if (tagName.equals("mailid") && tagDepth == 3) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.mail_internal_number = Integer.parseInt(xmlParser.getText());
//                            }
//                            break;
//                        }
//                        if (tagName.equals("mailattach") && tagDepth == 3) {
//
//                            attachments.clear();
//                            break;
//                        }
//                        if (tagName.equals("mailfrom") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailFrom(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailto") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailTo(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailcc") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailCc(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailbcc") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailBcc(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailsubject") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailSubject(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailreceived") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailReceived(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("mailbody") && tagDepth == 3) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                theMail.setMailBody(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        if (tagName.equals("attachment") && tagDepth == 4) {
//                            xmlParser.next();
//                            if (xmlParser.getEventType() == XmlPullParser.TEXT) {
//                                attachments.add(xmlParser.getText());
//                                break;
//                            }
//                        }
//                        break;
//                    // end tag
//                    // adding complex objects to corresponding lists
//                    case XmlPullParser.END_TAG:
//                        if (tagName.equals("mailattach") && tagDepth == 3 && !attachments.isEmpty()) {
//                            theMail.setAttachmentPaths(attachments);
//                        }
//                        if (tagName.equals("mail") && tagDepth == 2) {
//                            mailList.add(theMail);
//                        }
//                        break;
//                    default:
//                        break;
//                }
//                // move to next element
//                xmlParser.next();
//            }
//        } catch (XmlPullParserException | IOException e) {
//            e.printStackTrace();
//        }
//        return mailList;
        throw new UnsupportedOperationException();
    }
}
