package ru.job4j.food;

import java.util.ArrayList;
import java.util.List;

public class WareHouse implements Storage {

    private List<Food> storage = new ArrayList<>();

    @Override
    public List<Food> getStorage() {
        return storage;
    }

    @Override
    public void add(Food food) {
        storage.add(food);
    }

    @Override
    public boolean accept(Food food) {
        return food.percentsOfTimeToExpired() < 25;
    }

    @Override
    public List<Food> clear() {
        List<Food> result = storage;
        storage = new ArrayList<>();
        return result;
    }
}
