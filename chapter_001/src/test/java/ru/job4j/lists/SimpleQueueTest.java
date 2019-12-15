package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void whenAddThreeElementsThenGetThemFIFOOrder() {
        SimpleQueue<Integer> testQueue = new SimpleQueue<>();
        testQueue.push(1);
        testQueue.push(2);
        testQueue.push(3);

        assertThat(testQueue.poll(), is(1));
        assertThat(testQueue.poll(), is(2));
        assertThat(testQueue.poll(), is(3));
    }

    @Test
    public void whenGetElementFromEmptyQueueThenNull() {
        SimpleQueue<Integer> testQueue = new SimpleQueue<>();

        Integer result = testQueue.poll();
        Integer expected = null;

        assertThat(result, is(expected));
    }


}