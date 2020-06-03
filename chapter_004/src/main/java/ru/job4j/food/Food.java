package ru.job4j.food;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Food {

    private final String name;
    private final Calendar createDate;
    private final Calendar expireDate;
    private double price;
    private double discount = 0.0;

    public Food(String name, Calendar createDate, Calendar expireDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getExpireDate() {
        return expireDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int percentsOfTimeToExpired() {
        Calendar now = GregorianCalendar.getInstance();
        long baseDiff = expireDate.getTimeInMillis() - createDate.getTimeInMillis();
        long nowDiff = now.getTimeInMillis() - createDate.getTimeInMillis();
        return (int) ((double) nowDiff / baseDiff * 100);
    }
}
