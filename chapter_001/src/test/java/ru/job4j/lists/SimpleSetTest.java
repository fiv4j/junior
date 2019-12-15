package ru.job4j.lists;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void whenAddedThreeSameElementsAndIterateThenResultCountIsOne() {
        SimpleSet<String> test = new SimpleSet<>();
        test.add("hello");
        test.add("hello");
        test.add("hello");

        int expected = 1;
        int resultCount = 0;
        for (String elem : test) {
            if ("hello".equals(elem)) {
                resultCount++;
            }
        }

        assertThat(resultCount, is(expected));
    }

}