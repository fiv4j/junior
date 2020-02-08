package ru.job4j.oracle;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask() {
        return scanner.nextLine();
    }
}
