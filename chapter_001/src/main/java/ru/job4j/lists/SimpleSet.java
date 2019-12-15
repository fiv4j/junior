package ru.job4j.lists;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    DynamicArrayList<T> store = new DynamicArrayList<>();

    public void add(T value) {
        boolean isContain = false;
        for (T element : store) {
            if (value.equals(element)) {
                isContain = true;
                break;
            }
        }
        if (!isContain) {
            store.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return store.iterator();
    }
}
