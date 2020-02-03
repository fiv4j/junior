package ru.job4j.oracle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Random;

public class ServerOracle {

    private final Socket socket;

    public ServerOracle(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        List<String> botAnswers = List.of(
                "Yes, of course.",
                "No, It is impossible."
        );

        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream()));
        String ask;
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            if ("hello".equals(ask)) {
                out.println("Hello, dear friend, I'm a Oracle.");
                out.println();
                continue;
            }
            if ("bye".equals(ask)) {
                out.println("Bye, dear friend.");
                out.println();
                continue;
            }
            String answer = botAnswers.get(new Random().nextInt(botAnswers.size()));
            out.println(answer);
            out.println();
        } while (!ask.equals("bye"));
        socket.close();
        out.close();
        in.close();
    }

    public static void main(String[] args) throws IOException {
        try (final Socket socket = new ServerSocket(8189).accept()) {
            new ServerOracle(socket).start();
        }
    }
}
