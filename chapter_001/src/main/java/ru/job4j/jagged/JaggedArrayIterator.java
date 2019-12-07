package ru.job4j.jagged;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator<Integer> {

    private final int[][] values;
    private int row = 0;
    private int cell = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return  (row < values.length) && (cell < values[row].length);
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[row][cell];
        increaseIndexes();
        return result;
    }

    private void increaseIndexes() {
        if (cell + 1 < values[row].length) {
            cell++;
        } else {
            row++;
            cell = 0;
        }
    }
}
