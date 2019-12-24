package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(line -> {
                line = line.trim();
                if (!(line.equals("") || line.startsWith("#"))) {
                    String[] parts = line.split("=");
                    values.put(parts[0], parts[1]);
                }
            });
        } catch (Exception e) {
            System.out.print("I/O Error.");
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            return null;
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
