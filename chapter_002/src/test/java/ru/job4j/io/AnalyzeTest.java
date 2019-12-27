package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Ignore
public class AnalyzeTest {

    private Path log;
    private Path csv = Path.of("unavailable.csv");

    @Before
    public void before() throws IOException {
        log = Path.of("server.log");
        PrintWriter pw = new PrintWriter(log.toString());

        StringJoiner sj = new StringJoiner(System.lineSeparator())
                .add("200 10:56:01")
                .add("200 10:57:01")
                .add("400 10:58:01")
                .add("200 10:59:01")
                .add("500 11:01:02")
                .add("200 11:02:02")
                .add("400 11:02:03")
                .add("400 11:02:04")
                .add("400 11:02:05")
                .add("400 11:02:06")
                .add("200 11:02:07");

        pw.print(sj.toString());
        pw.close();
    }

    @After
    public void after() throws IOException {
        log = Path.of("server.log");
        if (Files.exists(log)) {
            Files.delete(log);
        }
        if (Files.exists(csv)) {
            Files.delete(csv);
        }
    }

    @Test
    public void whenRunMethodThenLogAndCSVFilesExist() {
        new Analyze().unavailable(log.toString(), csv.toString());
        boolean result = Files.exists(csv) && Files.exists(log);
        boolean expected = true;

        assertThat(result, is(expected));
    }

    @Test
    public void whenRunMethodThenCSVFileContainsExpectedString() throws IOException {
        new Analyze().unavailable(log.toString(), csv.toString());
        String expected = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("10:58:01;10:59:01;")
                .add("11:01:02;11:02:02;")
                .add("11:02:03;11:02:07;")
                .toString();

        BufferedReader br = new BufferedReader(new FileReader(csv.toString()));
        StringJoiner result = new StringJoiner(System.lineSeparator());
        String inLine;
        while ((inLine = br.readLine()) != null) {
            result.add(inLine);
        }
        br.close();

        assertThat(result.toString(), is(expected));
    }

    @Test
    public void whenLogFileNotExistsThenPrintError() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream stdout = System.out;
        System.setOut(new PrintStream(out));

        log = Path.of("not_existed_file");
        new Analyze().unavailable(log.toString(), csv.toString());

        assertThat(out.toString(), is("Input Error."));

        System.setOut(stdout);
    }
}