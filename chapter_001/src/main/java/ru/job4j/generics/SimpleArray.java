package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] values;
    private int innerIdx = 0;

    public SimpleArray(int size) {
        values = new Object[size];
    }

    public void add(T model) {
        values[innerIdx++] = model;
    }

    public void set(int index, T model) {
        values[index] = model;
    }

    public void remove(int index) {
        System.arraycopy(values, index + 1, values, index, values.length - 1 - index);
        values[values.length - 1] = null;
        innerIdx--;
    }

    public T get(int index) {
        return (T) values[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int iterIdx = 0;

            @Override
            public boolean hasNext() {
                return iterIdx <= innerIdx;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) values[iterIdx++];
            }
        };
    }
}
