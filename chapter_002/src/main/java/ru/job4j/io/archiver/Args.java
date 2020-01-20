package ru.job4j.io.archiver;

public class Args {

    private String[] args;

    public Args(String[] args) {
        this.args = args;
        if (args.length < 6) {
            throw new IllegalArgumentException();
        }
    }

    public String directory() {
        return args[findArgPosition("-d")];
    }

    public String exclude() {
        return args[findArgPosition("-e")];
    }

    public String output() {
        return args[findArgPosition("-o")];
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
