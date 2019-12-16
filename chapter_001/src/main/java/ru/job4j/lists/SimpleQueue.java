package ru.job4j.lists;

public class SimpleQueue<T> {

    private SimpleStack<T> inputStack = new SimpleStack<>();
    private SimpleStack<T> outputStack = new SimpleStack<>();

    public void push(T value) {
        inputStack.push(value);
    }

    public T poll() {
        T result = outputStack.poll();
        if (result == null) {
            fillOutputStack();
            result = outputStack.poll();
        }
        return result;
    }

    private void fillOutputStack() {
        T currentElement = inputStack.poll();
        while (currentElement != null) {
            outputStack.push(currentElement);
            currentElement = inputStack.poll();
        }
    }
}
