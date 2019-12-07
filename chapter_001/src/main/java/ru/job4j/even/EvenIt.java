package ru.job4j.even;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {

    private final int[] numbers;
    private int index;

    public EvenIt(final int[] numbers) {
        this.numbers = numbers;
        this.index = getNextIndex(-1);
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = numbers[index];
        index = getNextIndex(index);
        return result;
    }

    private int getNextIndex(int prevIndex) {
        int result = -1;

        if (numbers == null) {
            return result;
        }
        for (int i = prevIndex + 1; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
