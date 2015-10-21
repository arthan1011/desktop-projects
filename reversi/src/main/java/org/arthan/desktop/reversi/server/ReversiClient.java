package org.arthan.desktop.reversi.server;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.arthan.desktop.reversi.model.GameInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class ReversiClient {

    private final Socket socket;

    public ReversiClient(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("client created");
    }

    public GameInfo retriveInfo() {
        byte[] retrievedBytes = new byte[65];

        try (
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream()
        ){
            out.write(1);
            in.read(retrievedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GameInfo gameInfo = new GameInfo(retrievedBytes);
        return gameInfo;
    }
}
