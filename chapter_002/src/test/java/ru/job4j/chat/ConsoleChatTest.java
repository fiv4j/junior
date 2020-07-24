package ru.job4j.chat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConsoleChatTest {

    private String tmpDir = System.getProperty("java.io.tmpdir");
    private String textFilename = tmpDir + "/phrases";
    private String logFilename = tmpDir + "/log";

    @Before
    public void init() throws IOException {
        File phrasesFile = new File(textFilename);
        phrasesFile.createNewFile();

        try (PrintWriter fw = new PrintWriter(new FileWriter(phrasesFile))) {
            fw.print(
                    "Yes. No. Maybe."
            );
        }
    }

    @After
    public void clear() {
        File textFile = new File(textFilename);
        File logFile = new File(logFilename);

        if (textFile.exists()) {
            textFile.delete();
        }
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    @Test
    public void whenHasTextFileThenGetCorrectAnswersList() throws IOException {
        ConsoleChat chat = new ConsoleChat(textFilename);

        List<String> result = chat.getBotAnswers();
        List<String> expected = List.of(
                "Yes",
                "No",
                "Maybe"
        );

        assertThat(result, is(expected));
    }

    @Test(expected = IOException.class)
    public void whenTextFileNotExistsThenException() throws IOException {
        ConsoleChat chat = new ConsoleChat("not_exists_file");
    }

    @Ignore
    @Test
    public void whenEnterWordExitThenLogFileContainsIt() throws IOException {
        ConsoleChat chat = new ConsoleChat(textFilename, logFilename, new Input() {
            @Override
            public String ask() {
                return "exit";
            }
        });
        chat.start();

        String expected = "User: exit";
        String result;
        try (Scanner fs = new Scanner(new FileReader(logFilename))) {
            StringBuilder sb = new StringBuilder();
            while (fs.hasNextLine()) {
                sb.append(fs.nextLine());
            }
            result = sb.toString();
        }

        assertThat(result, is(expected));
    }

    @Test
    public void whenEnterWordSequenceEndsWithExitThenLogFileIsCorrect() throws IOException {
        File phrasesFile = new File(textFilename);
        if (phrasesFile.exists()) {
            phrasesFile.delete();
        }
        phrasesFile.createNewFile();
        try (PrintWriter fw = new PrintWriter(new FileWriter(phrasesFile))) {
            fw.print(
                    "Yes."
            );
        }

        ConsoleChat chat = new ConsoleChat(textFilename, logFilename, new Input() {
            String[] userMsgs = {"hello", "pause", "check pause", "resume", "exit"};
            int idx = 0;

            @Override
            public String ask() {
                return userMsgs[idx++];
            }
        });
        chat.start();

        String expected = new StringJoiner(System.lineSeparator(), "", "")
                .add("User: hello")
                .add("Bot: Yes")
                .add("User: pause")
                .add("User: check pause")
                .add("User: resume")
                .add("Bot: Yes")
                .add("User: exit")
                .toString();

        String result;
        try (Scanner fs = new Scanner(new FileReader(logFilename))) {
            StringJoiner sj = new StringJoiner(System.lineSeparator(), "", "");
            while (fs.hasNextLine()) {
                sj.add(fs.nextLine());
            }
            result = sj.toString();
        }

        assertThat(result, is(expected));
    }
}
