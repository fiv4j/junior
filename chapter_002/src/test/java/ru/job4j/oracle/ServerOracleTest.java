package ru.job4j.oracle;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerOracleTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void whenAskByeThenReturnByeString() throws IOException {
        testServer("bye", "Bye, dear friend.");
    }

    @Test
    public void whenAskHelloThenReturnHelloString() throws IOException {
        testServer("hello", "Hello, dear friend, I'm a Oracle.");
    }

    @Test
    public void whenAskRandomMsgThenReturnFromAnswersList() throws IOException {
        testServer("random message",
                "Yes, of course.", "No, It is impossible.");
    }

    private void testServer(String input, String... expectedOutputs) throws IOException {
        ByteArrayInputStream in;
        String[] expected = new String[expectedOutputs.length];
        if (input.equals("bye")) {
            in = new ByteArrayInputStream("bye".getBytes());
            for (int i = 0; i < expected.length; i++) {
                expected[i] = new StringJoiner(LINE_SEPARATOR, "", LINE_SEPARATOR)
                        .add(expectedOutputs[i])
                        .add("").toString();
            }
        } else {
            in = new ByteArrayInputStream(
                    new StringJoiner(LINE_SEPARATOR, "", LINE_SEPARATOR)
                            .add(input)
                            .add("bye").toString()
                            .getBytes());
            for (int i = 0; i < expected.length; i++) {
                expected[i] = new StringJoiner(LINE_SEPARATOR, "", LINE_SEPARATOR)
                        .add(expectedOutputs[i])
                        .add("")
                        .add("Bye, dear friend.")
                        .add("").toString();
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        ServerOracle server = new ServerOracle(socket);

        server.start();

        assertThat(
                out.toString(),
                expected.length > 1 ? CoreMatchers.anyOf(is(expected[0]), is(expected[1]))
                                    : is(expected[0])
        );
    }
}
