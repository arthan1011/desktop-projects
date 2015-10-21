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
    private ServerModel model = ServerModel.getInstance();
    private ServerSocket serverSocket;

    public ReversiServer(int testServerPort) {

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
                if (requestCode == 1) {
                    out.write(model.getBoardInfo());
                } else if (requestCode == 2) {
                    int x = in.read();
                    int y = in.read();
                    System.out.println(x + " " + y);
                    model.play(x, y);
                    out.write(model.getBoardInfo());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void reset() {
        model.restart();
    }
}
