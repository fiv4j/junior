package ru.job4j.tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream stdout = System.out;

    @Before
    public void before() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void after() {
        System.setOut(stdout);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"invalid", "0"}));
        input.askInt("Enter", 1);
        assertThat(out.toString(), is(String.format("Please enter a valid data again.%n")));
    }

    @Test
    public void whenNotInRangeInput() {
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"2", "1"}));
        input.askInt("Enter", 2);
        assertThat(out.toString(), is(String.format("Please select key from menu.%n")));
    }
}
