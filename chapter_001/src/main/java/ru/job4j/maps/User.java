package ru.job4j.maps;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, int year, int month, int day) {
        this.name = name;
        this.children = children;
        this.birthday = new GregorianCalendar(year, month, day);
    }
}
