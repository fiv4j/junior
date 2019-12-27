package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analyze {

    private static final List<String> UNAVAILABLE_STATUS = List.of("400", "500");
    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void unavailable(String source, String target) {

        try (BufferedReader br = new BufferedReader(new FileReader(source))) {

            boolean isUnavailable = false;
            StringBuilder out = new StringBuilder();
            String next;
            while ((next = br.readLine()) != null) {
                StatusTime current = new StatusTime(next);
                if (!isUnavailable && UNAVAILABLE_STATUS.contains(current.status)) {
                    out.append(current.time).append(';');
                    isUnavailable = true;
                }
                if (isUnavailable && !UNAVAILABLE_STATUS.contains(current.status)) {
                    out.append(current.time).append(';').append(LINE_SEPARATOR);
                    isUnavailable = false;
                }
            }
            this.writeToFile(target, out.toString());
        } catch (IOException e) {
            System.out.print("Input Error.");
        }
    }

    private void writeToFile(String filename, String data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println(data);
        } catch (IOException e) {
            System.out.println("Output Error.");
        }
    }

    private static class StatusTime {
        final String status;
        final String time;

        public StatusTime(String asLine) {
            String[] statusAndTime = asLine.split(" ");
            this.status = statusAndTime[0];
            this.time = statusAndTime[1];
        }
    }
}
