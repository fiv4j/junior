package ru.job4j.lists;

public class SimpleQueue<T> {

    private SimpleStack<T> store = new SimpleStack<>();

    public void push(T value) {
        store.push(value);
    }

    private SimpleStack<T> getReversedStack(SimpleStack<T> source) {
        SimpleStack<T> result = new SimpleStack<>();
        T currentElement = source.poll();
        while (currentElement != null) {
            result.push(currentElement);
            currentElement = source.poll();
        }
        return result;
    }

    public T poll() {
        SimpleStack<T> reversedStack = getReversedStack(store);
        T result = reversedStack.poll();
        store = getReversedStack(reversedStack);
        return result;
    }
}
