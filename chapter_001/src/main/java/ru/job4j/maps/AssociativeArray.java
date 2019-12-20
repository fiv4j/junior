package ru.job4j.maps;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class AssociativeArray<K, V> implements Iterable<K> {

    private int capacity;
    private Node<K, V>[] table;
    private int used = 0;
    private long modCounter = 0;

    public AssociativeArray() {
        this.capacity = 16;
        this.table = new Node[this.capacity];
    }

    public AssociativeArray(int capacity) {
        this.capacity = capacity;
        this.table = new Node[this.capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    boolean insert(K key, V value) {
        if (used == capacity) {
            resize();
        }
        Node<K, V> item = new Node<>(key, value);
        int index = getIndex(item.hash);
        if (table[index] != null) {
            return false;
        }
        table[index] = item;
        modCounter++;
        used++;
        return true;
    }

    V get(K key) {
        int currentIdx = getIndex(hash(key));
        return table[currentIdx] == null ? null : table[currentIdx].value;
    }

    boolean delete(K key) {
        boolean result = false;
        int currentIdx = getIndex(hash(key));
        if (table[currentIdx] != null) {
            table[currentIdx] = null;
            modCounter++;
            used--;
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int getIndex(int hash) {
        return hash == 0 ? 0 : hash & (capacity - 1);
    }

    private void resize() {
        modCounter++;
        capacity *= 2;
        used = 0;
        Node<K, V>[] oldTable = table;
        table = new Node[capacity];
        for (Node<K, V> item : oldTable) {
            insert(item.key, item.value);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final long expectedModCounter = modCounter;
            int iterCounter = IntStream.range(0, table.length)
                    .filter(i -> table[i] != null)
                    .findFirst().orElse(-1);

            @Override
            public boolean hasNext() {
                if (expectedModCounter != modCounter) {
                    throw new ConcurrentModificationException();
                }
                return iterCounter != -1 && iterCounter < table.length;
            }

            @Override
            public K next() {
                if (expectedModCounter != modCounter) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K result = table[iterCounter].key;
                ++iterCounter;
                while (iterCounter < table.length && table[iterCounter] == null) {
                    iterCounter++;
                }
                return result;
            }
        };
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.hash = key == null ? 0 : key.hashCode();
            this.value = value;
        }
    }
}
