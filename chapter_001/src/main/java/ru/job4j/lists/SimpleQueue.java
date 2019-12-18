package ru.job4j.lists;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public void push(T value) {
        in.push(value);
    }

    public T poll() {
        T result = out.poll();
        if (result == null) {
            fillOut();
            result = out.poll();
        }
        return result;
    }

    private void fillOut() {
        T current = in.poll();
        while (current != null) {
            out.push(current);
            current = in.poll();
        }
    }
}
