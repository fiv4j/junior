package ru.job4j.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientOracle {

    private final Socket socket;
    private final Input input;

    public ClientOracle(Socket socket, Input input) {
        this.socket = socket;
        this.input = input;
    }

    public void start() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String question;
        do {
            question = input.ask();
            out.println(question);
            String str = in.readLine();
            while (!str.isEmpty()) {
                System.out.println(str);
                str = in.readLine();
            }
        } while (!question.equals("bye"));
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new Socket("localhost", 8189)) {
            new ClientOracle(socket, new ConsoleInput()).start();
        }
    }
}
