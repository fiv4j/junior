package ru.job4j.maps;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AssociativeArrayTest {

    private AssociativeArray<Integer, String> map;

    @Before
    public void before() {
        map = new AssociativeArray<>();
        map.insert(1, "a");
        map.insert(2, "b");
        map.insert(3, "c");
    }

    @Test
    public void whenAddElementThenGetItByKeyShouldReturnValue() {
        map = new AssociativeArray<>();
        boolean boolResult = map.insert(12, "super");

        String expected = "super";
        String result = map.get(12);

        assertThat(boolResult, is(true));
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddElementAndThenAddingElementWithSameKeyShouldReturnFalse() {
        map = new AssociativeArray<>();
        map.insert(12, "super");
        boolean boolResult = map.insert(12, "SUPER");

        String expected = "super";
        String result = map.get(12);

        assertThat(boolResult, is(false));
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeleteElement() {
        assertThat(map.get(2), is("b"));

        boolean result = map.delete(2);
        assertThat(result, is(true));

        String expected = null;
        assertThat(map.get(2), is(expected));
    }

    @Test
    public void iteratorShouldReturnCorrectKeys() {
        Iterator<Integer> iterator = map.iterator();
        // 1
        boolean boolResult = iterator.hasNext();
        boolean boolExpected = true;
        assertThat(boolResult, is(boolExpected));
        Integer result = iterator.next();
        Integer expected = 1;
        assertThat(result, is(expected));

        // 2
        boolResult = iterator.hasNext();
        boolExpected = true;
        assertThat(boolResult, is(boolExpected));
        result = iterator.next();
        expected = 2;
        assertThat(result, is(expected));

        // 3
        boolResult = iterator.hasNext();
        boolExpected = true;
        assertThat(boolResult, is(boolExpected));
        result = iterator.next();
        expected = 3;
        assertThat(result, is(expected));
    }

    @Test
    public void whenArrayIsEmptyThenHasNextMethodReturnFalse() {
        map = new AssociativeArray<>();
        Iterator<Integer> iterator = map.iterator();

        boolean result = iterator.hasNext();
        boolean expected = false;
        assertThat(result, is(expected));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenArrayIsEmptyThenNextMethodThrowsException() {
        map = new AssociativeArray<>();
        Iterator<Integer> iterator = map.iterator();

        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenDuringIteratingAddElementShouldThrowsException() {
        Iterator<Integer> iterator = map.iterator();
        iterator.next();

        map.insert(6, "d");
        iterator.hasNext();
    }

    @Test
    public void whenAddElementsMoreThanCapacityThenCapacityIncreases() {
        map = new AssociativeArray<>(2);
        map.insert(null, "null");
        map.insert(1, "1");
        int expectedCap = 2;
        int resultCap = map.getCapacity();
        assertThat(resultCap, is(expectedCap));

        map.insert(2, "2");
        map.insert(3, "3");
        expectedCap = 4;
        resultCap = map.getCapacity();
        assertThat(resultCap, is(expectedCap));

        map.insert(4, "4");
        map.insert(5, "5");
        expectedCap = 8;
        resultCap = map.getCapacity();
        assertThat(resultCap, is(expectedCap));
    }

}