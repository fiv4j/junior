package ru.job4j.oracle;

import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientOracleTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void whenEnterByeThenPrintServerAnswerAndExit() throws IOException {
        ByteArrayInputStream fromServer = new ByteArrayInputStream(
                new StringJoiner(LINE_SEPARATOR, "", LINE_SEPARATOR)
                        .add("Server answer")
                        .add("")
                        .toString()
                        .getBytes()
        );
        ByteArrayOutputStream toServer = new ByteArrayOutputStream();

        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(fromServer);
        when(socket.getOutputStream()).thenReturn(toServer);

        new ClientOracle(socket, () -> "bye").start();

        assertThat(
                toServer.toString(),
                is(new StringJoiner(LINE_SEPARATOR, "", LINE_SEPARATOR)
                        .add("bye")
                        .toString())
        );
    }
}