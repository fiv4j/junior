package ru.job4j.array;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class XMLParserApp {

    private static long sum = 0;

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            if (qName.equals("entry")) {
                sum += Long.parseLong(attributes.getValue("href"));
            }
        }
    }

    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        int size = 5;
        String initXML = "init.xml";
        String transformedXML = "transformed.xml";
        String schema = "schema.xsl";

        Config configuration = new Config();
        configuration.init();
        StoreSQL sql = null;
        try {
            sql = new StoreSQL(configuration);
            sql.generate(size);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        StoreXML xml = new StoreXML(new File(initXML));
        try {
            xml.save(sql.load());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        ConvertXSQT.convert(
                new File(initXML),
                new File(transformedXML),
                new File(schema)
        );

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(transformedXML, handler);

        System.out.println(sum);
    }
}
