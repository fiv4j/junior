package ru.job4j.chat;

import java.io.*;
import java.util.*;

public class ConsoleChat {

    private List<String> botAnswers;
    private String logFilename = "chat.log";
    private Input input = new ConsoleInput();

    public ConsoleChat(String answersFilename) throws IOException {
        this.botAnswers = generateBotAnswers(new File(answersFilename));
    }

    public ConsoleChat(String answersFilename, String logFilename) throws IOException {
        this.botAnswers = generateBotAnswers(new File(answersFilename));
        this.logFilename = logFilename;
    }

    public ConsoleChat(String answersFilename, String logFilename, Input input) throws IOException {
        this.botAnswers = generateBotAnswers(new File(answersFilename));
        this.logFilename = logFilename;
        this.input = input;
    }

    public List<String> getBotAnswers() {
        return botAnswers;
    }

    public void start() {
        try (FileWriter fileWriter = new FileWriter(logFilename)) {

            boolean doPause = false;
            boolean doExit = false;

            do {
                System.out.print("User: ");
                String userMsg = input.ask();
                if ("pause".equals(userMsg)) {
                    doPause = true;
                }
                if (doPause && "resume".equals(userMsg)) {
                    doPause = false;
                }
                if ("exit".equals(userMsg)) {
                    doExit = true;
                }
                logMsg(fileWriter, "User: " + userMsg);

                if (!doPause && !doExit) {
                    String botMsg = "Bot: " + botAnswers.get(new Random().nextInt(botAnswers.size()));
                    System.out.println(botMsg);
                    logMsg(fileWriter, botMsg);
                }

            } while (!doExit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> generateBotAnswers(File botPhrasesFile) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader fr = new BufferedReader(new FileReader(botPhrasesFile))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = fr.readLine()) != null) {
                sb.append(line);
            }
            String text = sb.toString();

            result.addAll(Arrays.asList(text.replace(System.lineSeparator(), "").split("\\.[ ]*")));
        }

        return result;
    }

    private void logMsg(FileWriter fw, String msg) throws IOException {
        fw.write(msg + System.lineSeparator());
    }

    public static void main(String[] args) throws IOException {
        new ConsoleChat("answers.txt").start();
    }
}
