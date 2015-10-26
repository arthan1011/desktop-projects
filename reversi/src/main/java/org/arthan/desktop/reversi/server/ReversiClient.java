package org.arthan.desktop.reversi.server;

import org.arthan.desktop.reversi.Config;
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
    private final OutputStream out;
    private final InputStream in;

    private static final ReversiClient clientInstance = new ReversiClient(Config.getServerHost(), Config.getServerPort());

    private ReversiClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = socket.getOutputStream();
            in = socket.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GameInfo retriveInfo() {
        return sendSignal(ReversiProtocol.update());
    }

    public GameInfo play(int x, int y) {
        return sendSignal(ReversiProtocol.play(x, y));
    }

    public GameInfo reset() {
        return sendSignal(ReversiProtocol.reset());
    }

    private GameInfo sendSignal(ReversiProtocol.Signal signal) {
        byte[] retrievedBytes = new byte[65];

        try {
            out.write(signal.getBytes());
            in.read(retrievedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new GameInfo(retrievedBytes);
    }

    public static ReversiClient getInstance() {
        return clientInstance;
    }
}
