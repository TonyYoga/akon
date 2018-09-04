package ru.job4j.xmloptimization;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.*;
import java.io.*;

public class ConvertXSQT {

    private int value;

    public void convert(File source, File dest, File scheme) {
        //convert from xml to new xml file based on xlst scheme
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xlst = new StreamSource(scheme);
        try {
            Transformer transformer = factory.newTransformer(xlst);
            Source xml = new StreamSource(source);
            transformer.transform(xml, new StreamResult(dest));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public int parseNcalc(File sourse) {
        //pars xml file and count int fields
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            SAXpars saXpars = new SAXpars();
            parser.parse(sourse, saXpars);
            return value;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private class SAXpars extends DefaultHandler {
        //XML parser
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                value = value + Integer.parseInt(attributes.getValue("value"));
            }
        }
    }
}
