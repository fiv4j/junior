package ru.job4j.io.archiver;

import java.io.File;

public class MainZip {
    public static void main(String[] args) {
        Args arguments = new Args(args);
        String root = arguments.directory();
        String ext = arguments.exclude();
        File target = arguments.output();

        System.out.println("Root: " + root);
        System.out.println("Ext: " + ext);
        System.out.println("Target: " + target.getName());
    }
}
