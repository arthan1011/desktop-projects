package org.arthan.desktop.reversi.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class ReversiServer {
    public static final String INITIAL_GAME_STATE_INFO_STRING = "00000000000000000000000000021000000120000000000000000000000000001";

    public ReversiServer(int testServerPort) {

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(testServerPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("client accepted");
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream();

                int requestCode = in.read();
                out.write(INITIAL_GAME_STATE_INFO_STRING.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
