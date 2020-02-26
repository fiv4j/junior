package ru.job4j.tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = s -> new PrintStream(out).println(s);

    @Test
    public void whenExit() {
        Input input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction("Stub action.");
        List<UserAction> actions = new ArrayList<>();
        actions.add(action);
        new StartUI(input, new Tracker(), System.out::println).init(actions);
        assertThat(action.isCall(), is(true));
    }

    @Test
    public void whenPrintMenu() {
        Input input = new StubInput(new String[] {"0"});
        UserAction action = new StubAction("Stub action.");
        List<UserAction> actions = new ArrayList<>();
        actions.add(action);
        new StartUI(input, new Tracker(), output).init(actions);

        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("Menu.")
                .add("0. Stub action.")
                .toString();
        assertThat(out.toString(), is(expect));
    }
}
