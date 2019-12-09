package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddSomeElementsGettingSThemShouldReturnCorrectAnswers() {
        SimpleArray<Integer> testArray = new SimpleArray<>(2);
        testArray.add(1);
        testArray.add(2);

        assertThat(testArray.get(0), is(1));
        assertThat(testArray.get(1), is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetIndexMoreThanArraysSizeThenThrowException() {
        SimpleArray<Integer> testArray = new SimpleArray<>(2);
        testArray.add(1);
        testArray.add(2);

        testArray.get(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddElementWithIndexMoreThanArraysSizeThenThrowException() {
        SimpleArray<Integer> testArray = new SimpleArray<>(1);

        testArray.add(10);
        testArray.add(20);
    }

    @Test
    public void settingValueToElementWorkCorrect() {
        SimpleArray<Integer> testArray = new SimpleArray<>(2);
        testArray.add(1);
        testArray.add(2);

        testArray.set(1, 20);

        assertThat(testArray.get(1), is(20));
    }

    @Test
    public void whenRemoveElementThenRightElementsMoveToArraysBegin() {
        SimpleArray<Integer> testArray = new SimpleArray<>(3);
        testArray.add(10);
        testArray.add(20);
        testArray.add(30);

        assertThat(testArray.get(0), is(10));
        assertThat(testArray.get(1), is(20));
        assertThat(testArray.get(2), is(30));

        testArray.remove(0);
        assertThat(testArray.get(0), is(20));
        assertThat(testArray.get(1), is(30));
    }

    @Test
    public void iteratorTest() {
        SimpleArray<Integer> testArray = new SimpleArray<>(5);
        testArray.add(1);
        testArray.add(2);
        testArray.add(220158);
        testArray.add(3);

        boolean expected = true;
        boolean result = false;
        for (var elem : testArray) {
            if (elem == 220158) {
                result = true;
                break;
            }
        }

        assertThat(result, is(expected));
    }
}