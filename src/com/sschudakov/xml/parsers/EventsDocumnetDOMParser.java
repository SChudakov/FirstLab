package com.sschudakov.xml.parsers;

import com.sschudakov.lesson_9.bin.CallPrices;
import com.sschudakov.lesson_9.bin.Parameters;
import com.sschudakov.lesson_9.bin.Tariff;
import com.sschudakov.xml.bin.Event;
import com.sschudakov.xml.bin.Events;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Semen Chudakov on 18.11.2017.
 */
public class EventsDocumnetDOMParser {

    public static List<Events> parse(Document document) {
        List<Events> result = new ArrayList<>();

        Node tariffsNode = document.getChildNodes().item(0);
        System.out.println("\n\n");
        System.out.println("tariffs node" + tariffsNode);

        if (!tariffsNode.getNodeName().equals("tns:tariffs")) {
            throw new IllegalArgumentException("given document has no tariffs node");
        }
        NodeList tariffs = tariffsNode.getChildNodes();

        for (int i = 0; i < tariffs.getLength(); i++) {
            System.out.println(tariffs.item(i).getClass());
            if (tariffs.item(i).getNodeName().equals("tariff")) {
                result.add(parseTariff(tariffs.item(i)));
            }
        }
        return result;
    }

    private static Event parseTariff(Node tariff) {
//        if (tariff.getNodeType() == Node.ELEMENT_NODE) {
        if (!tariff.getNodeName().equals("tariff")) {
            System.out.println("node name: " + tariff.getNodeName());
            throw new IllegalArgumentException("node " + tariff + " is not a tariff node");
        }
        NodeList tariffNodes = tariff.getChildNodes();

        for (int i = 0; i < tariffNodes.getLength(); i++) {
            System.out.println(tariffNodes.item(i));
        }


        Event result = new Event(
                parseTariffName(tariffNodes.item(1)),
                parseOperatorName(tariffNodes.item(3)),
                parsePayroll(tariffNodes.item(5)),
                parseCallPrices(tariffNodes.item(7)),
                parseSMSPrice(tariffNodes.item(9)),
                parseParameters(tariffNodes.item(11))

        );
//        }
        return result;
    }

    private static LastFirsMiddleName parseTariffName(Node tariffName) {
        if (!tariffName.getNodeName().equals("name")) {
            throw new IllegalArgumentException("node " + tariffName + " is not a tariff name node");
        }
        return tariffName.getNodeValue();
    }

    private static String parseOperatorName(Node operatorName) {
        if (!operatorName.getNodeName().equals("operator_name")) {
            throw new IllegalArgumentException("node " + operatorName + " is not a operator name node");
        }
        return operatorName.getNodeValue();
    }

    private static int parsePayroll(Node payroll) {
        if (!payroll.getNodeName().equals("payroll")) {
            throw new IllegalArgumentException("node " + payroll + " is not a operator name node");
        }
        return Integer.parseInt(payroll.getNodeValue());
    }

    private static CallPrices parseCallPrices(Node callPrices) {
        NodeList prices = callPrices.getChildNodes();
        CallPrices result = new CallPrices(
                parseInNetworkPrice(prices.item(0)),
                parseOutOfNetworkPrice(prices.item(1)),
                parseCordedTelephonePrice(prices.item(2))
        );
        return result;
    }

    private static int parseInNetworkPrice(Node inNetworkPrice) {
        if (!inNetworkPrice.getNodeName().equals("in_network_price")) {
            throw new IllegalArgumentException("node " + inNetworkPrice + " is not a operator name node");
        }
        return Integer.parseInt(inNetworkPrice.getNodeValue());
    }

    private static int parseOutOfNetworkPrice(Node outOfNetworkPrice) {
        if (!outOfNetworkPrice.getNodeName().equals("out_of_network_price")) {
            throw new IllegalArgumentException("node " + outOfNetworkPrice + " is not a operator name node");
        }
        return Integer.parseInt(outOfNetworkPrice.getNodeValue());
    }

    private static int parseCordedTelephonePrice(Node cordedTelephonePrice) {
        if (!cordedTelephonePrice.getNodeName().equals("corded_telephone_price")) {
            throw new IllegalArgumentException("node " + cordedTelephonePrice + " is not a operator name node");
        }
        return Integer.parseInt(cordedTelephonePrice.getNodeValue());
    }


    private static int parseSMSPrice(Node smsPrice) {
        if (!smsPrice.getNodeName().equals("sms_price")) {
            throw new IllegalArgumentException("node " + smsPrice + " is not a operator name node");
        }
        return Integer.parseInt(smsPrice.getNodeValue());
    }

    private static Parameters parseParameters(Node tariffName) {
        NodeList prices = tariffName.getChildNodes();
        Parameters result = new Parameters(
                parseNumOfFavouriteNumbers(prices.item(0)),
                parseBilling(prices.item(1)),
                parseInvolvementPrice(prices.item(2))
        );
        return result;
    }

    private static int parseNumOfFavouriteNumbers(Node cordedTelephonePrice) {
        if (!cordedTelephonePrice.getNodeName().equals("num_of_favourite_numbers")) {
            throw new IllegalArgumentException("node " + cordedTelephonePrice + " is not a operator name node");
        }
        return Integer.parseInt(cordedTelephonePrice.getNodeValue());
    }

    private static int parseBilling(Node cordedTelephonePrice) {
        if (!cordedTelephonePrice.getNodeName().equals("billing")) {
            throw new IllegalArgumentException("node " + cordedTelephonePrice + " is not a operator name node");
        }
        return Integer.parseInt(cordedTelephonePrice.getNodeValue());
    }

    private static int parseInvolvementPrice(Node cordedTelephonePrice) {
        if (!cordedTelephonePrice.getNodeName().equals("involvement_price")) {
            throw new IllegalArgumentException("node " + cordedTelephonePrice + " is not a operator name node");
        }
        return Integer.parseInt(cordedTelephonePrice.getNodeValue());
    }


}
