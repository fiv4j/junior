package ru.job4j.statistic;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    Analize test = new Analize();
    List<Analize.User> prev;
    List<Analize.User> curr;

    @Before
    public void before() {
        prev = List.of(
                new Analize.User(1, "ivan"),
                new Analize.User(2, "inna")
        );
    }

    @Test
    public void whenNoChanges() {
        curr = List.of(
                new Analize.User(1, "ivan"),
                new Analize.User(2, "inna")
        );

        Analize.Info result = test.diff(prev, curr);
        Analize.Info expected = new Analize.Info(0, 0, 0);

        assertThat(result, is(expected));
    }

    @Test
    public void whenAllElementsDeleted() {
        curr = List.of();

        Analize.Info result = test.diff(prev, curr);
        Analize.Info expected = new Analize.Info(0, 0, 2);

        assertThat(result, is(expected));
    }

    @Test
    public void whenAllElementsAdded() {
        prev = List.of();
        curr = List.of(
                new Analize.User(1, "ivan"),
                new Analize.User(2, "inna")
        );

        Analize.Info result = test.diff(prev, curr);
        Analize.Info expected = new Analize.Info(2, 0, 0);

        assertThat(result, is(expected));
    }

    @Test
    public void whenAllElementsChanged() {
        curr = List.of(
                new Analize.User(1, "stas"),
                new Analize.User(2, "simona")
        );

        Analize.Info result = test.diff(prev, curr);
        Analize.Info expected = new Analize.Info(0, 2, 0);

        assertThat(result, is(expected));
    }

    @Test
    public void whenOneChangedOneAddedOneDeleted() {
        curr = List.of(
                new Analize.User(1, "stas"),
                new Analize.User(3, "simona")
        );

        Analize.Info result = test.diff(prev, curr);
        Analize.Info expected = new Analize.Info(1, 1, 1);

        assertThat(result, is(expected));
    }

}