package ru.job4j.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;

public class Search {

    public List<File> files(String parent, List<String> exts, Predicate<String> inExts) throws FileNotFoundException {
        List<File> result = new ArrayList<>();
        File first = new File(parent);
        if (!first.exists()) {
            throw new FileNotFoundException();
        }

        Queue<File> queue = new LinkedList<>();
        queue.offer(first);
        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (current.isFile() && inExts.test(getFileExt(current))) {
                result.add(current);
            }
            if (current.isDirectory() && current.listFiles() != null) {
                for (var element : current.listFiles()) {
                    queue.offer(element);
                }
            }
        }

        return result;
    }

    private String getFileExt(File file) {
        String[] partsOfFilename = file.getName().split("\\.");
        return partsOfFilename.length < 2 ? "" : partsOfFilename[partsOfFilename.length - 1];
    }
}
