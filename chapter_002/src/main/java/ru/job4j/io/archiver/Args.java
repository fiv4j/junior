package ru.job4j.io.archiver;

import java.io.File;

public class Args {

    private String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    public String directory() {
        return args[findArgPosition("-d")];
    }

    public String exclude() {
        return args[findArgPosition("-e")];
    }

    public File output() {
        String outputFile = args[findArgPosition("-o")];
        return new File(outputFile);
    }

    private int findArgPosition(String flag) {
        int result = 0;
        for (int i = 0; i < args.length; i++) {
            if (flag.equals(args[i])) {
                result = ++i;
                break;
            }
        }
        return result;
    }
}
