package org.arthan.desktop.reversi.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class ReversiServer {
    private ServerModel model = ServerModel.getInstance();
    private ServerSocket serverSocket;

    private ReversiServer() {
    }

    public static ReversiServer createServer(int serverPort) {
        ReversiServer reversiServer = new ReversiServer();
        try {
            reversiServer.initServer(serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reversiServer;
    }

    private void initServer(int serverPort) throws IOException {
        serverSocket = new ServerSocket(serverPort);

        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ExecutorService threadPool = Executors.newFixedThreadPool(2);
                    threadPool.submit(() -> {
                        try {
                            handleSocket(socket);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }).start();
    }

    private void handleSocket(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        while (true) {
            int requestCode = in.read();
            if (requestCode == 2) {
                int x = in.read();
                int y = in.read();
                model.play(x, y);
            } else if (requestCode == 3) {
                model.restart();
            }
            out.write(model.getBoardInfo());
        }

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
