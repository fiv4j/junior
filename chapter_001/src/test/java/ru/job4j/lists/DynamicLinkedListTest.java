package ru.job4j.lists;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);
    }

    @Test
    public void whenAddedThreeElementsGetsElementWithIndex2ThenResultIs10() {
        int expected = 10;
        int result = list.get(2);

        assertThat(result, is(expected));
    }

    @Test
    public void whenListIsEmptyGetsElementWithIndex2ThenResultIsNull() {
        list = new DynamicLinkedList<>();

        Integer expected = null;
        Integer result = list.get(2);

        assertThat(result, is(expected));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenDoConcurrentModificationThenThrowException() {
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        list.add(30);
        iterator.next();
    }
}