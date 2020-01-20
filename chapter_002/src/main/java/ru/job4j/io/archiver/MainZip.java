package ru.job4j.io.archiver;

import java.io.FileNotFoundException;

public class MainZip {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Args arguments = new Args(args);
            String root = arguments.directory();
            String ext = arguments.exclude();
            String target = arguments.output();

            new Zip().packDir(root, target, ext);

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Wrong arguments.");
        }
    }
}
