package ru.job4j.tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindByNameActionTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = s -> new PrintStream(out).println(s);

    @Test
    public void whenFoundByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Difficult name");
        tracker.add(item);
        Input input = new StubInput(new String[] {"Difficult name"});

        new FindByNameAction("Show all.").execute(input, tracker, output);
        String expected = new StringJoiner(" : ", "", System.lineSeparator())
                .add(item.getId())
                .add(item.getName())
                .toString();

        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenNotFoundByName() {
        Tracker tracker = new Tracker();
        Item item = new Item("Difficult name");
        tracker.add(item);
        Input input = new StubInput(new String[] {"Different name"});

        new FindByNameAction("Show all.").execute(input, tracker, output);
        String expected = new StringJoiner("", "", System.lineSeparator())
                .add("No items found.")
                .toString();

        assertThat(out.toString(), is(expected));
    }
}
