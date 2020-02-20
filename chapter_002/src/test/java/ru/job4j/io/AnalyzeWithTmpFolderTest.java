package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.StringJoiner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeWithTmpFolderTest {

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void whenRunMethodThenCSVContainsCorrectOutput() throws IOException {
        File source = tmpFolder.newFile("init.log");
        File target = tmpFolder.newFile("unavailable_time.csv");
        try (PrintWriter pw = new PrintWriter(source)) {
            String initData = new StringJoiner(System.lineSeparator())
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
                    .add("200 11:02:07")
                    .toString();
            pw.print(initData);
        }

        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringJoiner result = new StringJoiner(System.lineSeparator());
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        String expected = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("10:58:01;10:59:01;")
                .add("11:01:02;11:02:02;")
                .add("11:02:03;11:02:07;")
                .toString();

        assertThat(result.toString(), is(expected));
    }
}
