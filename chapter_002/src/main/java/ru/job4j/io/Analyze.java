package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analyze {

    private static final List<String> UNAVAILABLE_STATUS = List.of("400", "500");

    public void unavailable(String source, String target) {

        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(new FileOutputStream(target))) {

            boolean isUnavailable = false;
            String next;
            while ((next = br.readLine()) != null) {
                StatusTime current = new StatusTime(next);
                if (!isUnavailable && UNAVAILABLE_STATUS.contains(current.status)) {
                    pw.print(current.time + ";");
                    isUnavailable = true;
                }
                if (isUnavailable && !UNAVAILABLE_STATUS.contains(current.status)) {
                    pw.println(current.time + ";");
                    isUnavailable = false;
                }
            }
        } catch (IOException e) {
            System.out.print("I/O Error.");
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
