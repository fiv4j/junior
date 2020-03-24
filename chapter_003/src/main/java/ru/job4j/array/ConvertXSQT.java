package ru.job4j.array;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ConvertXSQT {

    public static void convert(File source, File dest, File scheme) {
        String xml = readXML(source);
        String xsl = readXML(scheme);
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(
                    new StreamSource(new ByteArrayInputStream(xsl.getBytes()))
            );
            transformer.transform(
                    new StreamSource(new ByteArrayInputStream(xml.getBytes())),
                    new StreamResult(dest)
            );
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static String readXML(File file) {
        StringBuilder result = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                result.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
