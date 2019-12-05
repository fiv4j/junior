package ru.job4j.jagged;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JaggedArrayIterator implements Iterator<Integer> {

    private final int[][] values;
    private int row = 0;
    private int cell = 0;
    private int idx = 0;

    public JaggedArrayIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return idx < this.sumLength();
    }

    @Override
    public Integer next() {
        if (this.idx >= sumLength()) {
            throw new NoSuchElementException();
        }
        int result = values[this.row][this.cell];
        increaseIdx();
        return result;
    }

    private int sumLength() {
        int result = 0;
        for (int[] inner : this.values) {
            result += inner.length;
        }
        return result;
    }

    private void increaseIdx() {
        this.idx++;
        if (this.cell + 1 < values[this.row].length) {
            this.cell++;
        } else {
            this.row++;
            this.cell = 0;
        }
    }
}
