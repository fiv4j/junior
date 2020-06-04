package ru.job4j.food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlQuality {

    private final List<Storage> storages = new ArrayList<>();

    public ControlQuality(Storage... storages) {
        this.storages.addAll(Arrays.asList(storages));
    }

    public void distribute(Food food) {
        for (Storage storage : storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    public void distribute(List<Food> foods) {
        for (Food food : foods) {
            distribute(food);
        }
    }

    public void redistribute() {
        List<Food> tempStorage = new ArrayList<>();
        for (Storage storage : storages) {
            tempStorage.addAll(storage.clear());
        }

        for (Food food : tempStorage) {
            for (Storage storage : storages) {
                if (storage.accept(food)) {
                    storage.add(food);
                    break;
                }
            }
        }
    }
}
