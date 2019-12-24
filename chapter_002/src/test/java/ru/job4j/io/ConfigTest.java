package ru.job4j.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenLoadThenIgnoreCommentsAndBlankLinesAndReturnValidValues() {
        String path = "../app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio")
        );
    }

    @Test
    public void whenLoadThenValueOfNotExistedKeyReturnNull() {
        String path = "../app.properties";
        Config config = new Config(path);
        config.load();

        String result = config.value("hibernate");
        String expected = null;

        assertThat(result, is(expected));
    }

    @Test
    public void whenTryLoadingNotExistedFileThenGetErrorMessage() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(out));

        String path = "not_existed_file";
        Config config = new Config(path);
        config.load();

        assertThat(out.toString(), is("I/O Error."));

        System.setOut(stdout);
    }
}