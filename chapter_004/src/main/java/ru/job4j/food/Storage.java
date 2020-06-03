package ru.job4j.food;

import java.util.List;

public interface Storage {

    List<Food> getStorage();

    void add(Food food);

    boolean accept(Food food);

    List<Food> clear();
}
