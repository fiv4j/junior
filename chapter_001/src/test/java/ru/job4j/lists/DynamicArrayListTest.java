package ru.job4j.lists;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicArrayListTest {

    private DynamicArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new DynamicArrayList<>();
        list.add(10);
        list.add(20);
    }

    @Test
    public void whenAddMoreElementsThenSizeContainerIncreased() {
        list = new DynamicArrayList<>(0);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        int result = list.get(3);
        int expected = 40;

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