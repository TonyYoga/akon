package ru.job4j.stock;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XMLparser {
    private String filename;
    private List<Order> orderList;

    public XMLparser(String filename) {
        this.filename = filename;
        orderList = new ArrayList<>();
        orderList.add(null); // add element with index 0
    }

    public List<Order> historyParser() {

        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(filename, new FileInputStream(filename));

            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement() && xmlr.getLocalName().equals("AddOrder")) {
                    int indexID = Integer.parseInt(xmlr.getAttributeValue(4)); //idOrder = index
                    String bookname = xmlr.getAttributeValue(0);
                    Order.Operation op = xmlr.getAttributeValue(1).equals("SELL") ? Order.Operation.SELL : Order.Operation.BUY;
                    double price = Double.parseDouble(xmlr.getAttributeValue(2));
                    int volume = Integer.parseInt(xmlr.getAttributeValue(3));
                    orderList.add(indexID, new Order(indexID, bookname, op, price, volume, true));
                } else if (xmlr.isStartElement() && xmlr.getLocalName().equals("DeleteOrder")) {
                    int indexID = Integer.parseInt(xmlr.getAttributeValue(1));
                    orderList.get(indexID).setAvailable(false);
                }

            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return orderList;

    }


}
