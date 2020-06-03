package ru.job4j.food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {

    private List<Food> storage = new ArrayList<>();

    public List<Food> getStorage() {
        return storage;
    }

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return food.percentsOfTimeToExpired() >= 100;
    }

    @Override
    public List<Food> clear() {
        List<Food> result = storage;
        storage = new ArrayList<>();
        return result;
    }
}
