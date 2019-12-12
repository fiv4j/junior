package ru.job4j.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicLinkedList<E> implements Iterable<E> {

    private long modCount = 0;
    private Node<E> first;
    private int size = 0;

    public void add(E value) {
        Node<E> newItem = new Node<>(value);
        newItem.next = this.first;
        this.first = newItem;
        size++;
        modCount++;
    }

    public E get(int index) {
        if (index >= size) {
            return null;
        }
        Node<E> result = first;
        int idx = 1;
        while (idx <= index) {
            result = result.next;
            idx++;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final long expectedModCount = modCount;
            int iterIndex = 0;
            Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (iterIndex >= size) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = currentNode.data;
                try {
                    currentNode = currentNode.next;
                } catch (NullPointerException ex) {
                    currentNode = null;
                }
                iterIndex++;
                return result;
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}
