package ru.job4j.tree;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionalEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddedElementAlreadyExistThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);

        boolean result = tree.add(1, 2);
        boolean expected = false;
        assertThat(result, is(expected));

    }

    @Test
    public void whenIteratingBy6ElementsThenHasNextAndNextReturnCorrectValues() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        Iterator<Integer> iterator = tree.iterator();

        for (int i = 1; i <= 6; i++) {
            assertThat(iterator.hasNext(), is(true));
            assertThat(iterator.next(), is(i));
        }
    }

    @Test
    public void whenIteratingThenAfterLastElementHasNextReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        Iterator<Integer> iterator = tree.iterator();
        for (int i = 1; i <= 6; i++) {
            iterator.next();
        }

        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratingThenAfterLastElementNextThrowException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        Iterator<Integer> iterator = tree.iterator();
        for (int i = 1; i <= 6; i++) {
            iterator.next();
        }

        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratingAndAddNewElementThenNextThrowException() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        Iterator<Integer> iterator = tree.iterator();
        iterator.next();

        tree.add(5, 7);

        iterator.next();
    }

    @Test
    public void whenTreeIsBinaryReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);

        boolean result = tree.isBinary();
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test
    public void whenTreeIsNotBinaryReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        boolean result = tree.isBinary();
        boolean expected = false;

        assertThat(result, is(expected));
    }
}
