package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayList<E> implements Iterable<E> {
    private long modCount = 0L;
    private Object[] container;
    private int currentIndex = 0;

    public DynamicArrayList(int size) {
        container = new Object[size];
    }

    public DynamicArrayList() {
        this(10);
    }

    private void checkSize() {
        if (currentIndex == container.length) {
            increaseSize();
        }
    }

    private void increaseSize() {
        container = Arrays.copyOf(container, container.length * 2 + 1);
    }

    public void add(E value) {
        modCount++;
        checkSize();
        container[currentIndex++] = value;
    }

    public E get(int index) {
        if (index >= currentIndex) {
            return null;
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final long expectedModCount = modCount;
            int iterIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (iterIndex <= container.length) {
                    return true;
                }
                return false;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(iterIndex++);
            }
        };
    }
}
