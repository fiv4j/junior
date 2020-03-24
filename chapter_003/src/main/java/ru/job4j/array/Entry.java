package ru.job4j.array;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso(Entries.class)
public class Entry {

    @XmlElement(required = true)
    private int field;

    public Entry() {

    }

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Entry{field=" + field + '}';
    }
}
