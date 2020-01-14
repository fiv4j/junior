package ru.job4j.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static boolean exit = false;
    private static boolean paused = false;

    private static String userMsg;
    private static String botMsg;

    private static final File LOG_FILE = new File("session.log");
    private static final File BOT_PHRASE_FILE = new File("answers.txt");
    private static final List<String> BOT_ANSWERS = new ArrayList<>();

    static {
        try (BufferedReader fr = new BufferedReader(new FileReader(BOT_PHRASE_FILE))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = fr.readLine()) != null) {
                sb.append(line);
            }
            String text = sb.toString();

            BOT_ANSWERS.addAll(Arrays.asList(text.replace(System.lineSeparator(), "").split("\\.")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                FileWriter fileWriter = new FileWriter(LOG_FILE)) {
            do {
                System.out.print("User: ");
                userMsg = consoleReader.readLine();
                checkUserMsg();

                createBotMsg();
                if (botMsg != null) {
                    System.out.println("Bot: " + botMsg);
                }

                logChat(fileWriter);

            } while (!exit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkUserMsg() {
        if ("pause".equals(userMsg)) {
            paused = true;
        }
        if (paused && "resume".equals(userMsg)) {
            paused = false;
        }
        if ("exit".equals(userMsg)) {
            exit = true;
        }
    }

    private static void createBotMsg() {
        if (paused || exit) {
            botMsg = null;
            return;
        }
        botMsg = BOT_ANSWERS.get(new Random().nextInt(BOT_ANSWERS.size()));
    }

    private static void logChat(FileWriter fw) throws IOException {
        fw.write("User: " + userMsg + System.lineSeparator());
        if (botMsg != null) {
            fw.write("Bot: " + botMsg + System.lineSeparator());
        }
    }
}
