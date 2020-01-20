package ru.job4j.chat;

import java.io.*;
import java.util.*;

public class ConsoleChat {

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

            Map<String, Boolean> statuses = new HashMap<>();
            statuses.put("exit", false);
            statuses.put("pause", false);

            do {
                System.out.print("User: ");
                String userMsg = consoleReader.readLine();
                checkUserMsg(userMsg, statuses);

                String botMsg = createBotMsg(statuses);
                if (botMsg != null) {
                    System.out.println("Bot: " + botMsg);
                }

                logChat(fileWriter, userMsg, botMsg);

            } while (!statuses.get("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkUserMsg(String userMsg, Map<String, Boolean> statuses) {
        if ("pause".equals(userMsg)) {
            statuses.put("pause", true);
        }
        if (statuses.get("pause") && "resume".equals(userMsg)) {
            statuses.put("pause", false);
        }
        if ("exit".equals(userMsg)) {
            statuses.put("exit", true);
        }
    }

    private static String createBotMsg(Map<String, Boolean> statuses) {
        if (statuses.get("pause") || statuses.get("exit")) {
            return null;
        }
        return BOT_ANSWERS.get(new Random().nextInt(BOT_ANSWERS.size()));
    }

    private static void logChat(FileWriter fw, String userMsg, String botMsg) throws IOException {
        fw.write("User: " + userMsg + System.lineSeparator());
        if (botMsg != null) {
            fw.write("Bot: " + botMsg + System.lineSeparator());
        }
    }
}
