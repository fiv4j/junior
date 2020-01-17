package ru.job4j.recurrence;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NodeTest {
    Node<Integer> first = new Node<>(1);
    Node<Integer> two = new Node<>(2);
    Node<Integer> third = new Node<>(3);
    Node<Integer> four = new Node<>(4);
    Node<Integer> five = new Node<>(5);

    @Test
    public void whenHasNotCycle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;

        boolean expected = false;
        boolean result = Node.hasCycle(first);

        assertThat(result, is(expected));
    }

    @Test
    public void whenHasCycleFromEndToBegin() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = five;
        five.next = first;

        boolean expected = true;
        boolean result = Node.hasCycle(first);

        assertThat(result, is(expected));
    }

    @Test
    public void whenHasCycleInTheMiddle() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = two;

        boolean expected = true;
        boolean result = Node.hasCycle(first);

        assertThat(result, is(expected));
    }
}