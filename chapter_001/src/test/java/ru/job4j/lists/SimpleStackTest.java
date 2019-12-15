package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenGetAddedElementsAtReverseOrder() {
        SimpleStack<String> testStack = new SimpleStack<>();

        testStack.push("one");
        testStack.push("two");
        testStack.push("three");

        assertThat(testStack.poll(), is("three"));
        assertThat(testStack.poll(), is("two"));
        assertThat(testStack.poll(), is("one"));
    }

    @Test
    public void whenGetElementsFromEmptyStackThenNull() {
        SimpleStack<String> testStack = new SimpleStack<>();

        String expected = null;
        String result = testStack.poll();

        assertThat(result, is(expected));

    }
}