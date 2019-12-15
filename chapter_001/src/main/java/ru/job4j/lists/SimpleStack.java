package ru.job4j.lists;

public class SimpleStack<T> {

    private DynamicLinkedList<T> store = new DynamicLinkedList<>();

    public T poll() {
        return store.remove(0);
    }

    public void push(T value) {
        store.add(value);
    }
}
