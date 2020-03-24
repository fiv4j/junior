package ru.job4j.array;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StoreXML {

    private final File target;

    public StoreXML(final File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        Entries entries = new Entries(list);
        try (FileWriter fw = new FileWriter(target)) {
            JAXBContext context = JAXBContext.newInstance(Entry.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, fw);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
