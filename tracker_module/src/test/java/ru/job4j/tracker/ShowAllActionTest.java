package ru.job4j.tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;
import java.util.function.Consumer;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShowAllActionTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = s -> new PrintStream(out).println(s);

    @Test
    public void whenCheckOut() {

        Tracker tracker = new Tracker();
        Item item = new Item("Test item");
        tracker.add(item);

        new ShowAllAction("Show all.").execute(new StubInput(new String[] {}), tracker, output);

        String expected = new StringJoiner(" : ", "", System.lineSeparator())
                .add(item.getId())
                .add(item.getName())
                .toString();
        assertThat(out.toString(), is(expected));
    }
}
