package org.arthan.desktop.reversi.server;

import java.util.Arrays;

/**
 * Created by ashamsiev on 22.10.2015
 */
public class ReversiProtocol {

    private static final byte UPDATE_CODE = 1;
    private static final byte PLAY_CODE = 2;
    private static final byte RESET_CODE = 3;

    public static Signal update() {
        return new Signal(new byte[] {UPDATE_CODE});
    }

    public static Signal play(int x, int y) {
        return new Signal(new byte[] {PLAY_CODE, (byte) x, (byte) y});
    }

    public static Signal reset() {
        return new Signal(new byte[] {RESET_CODE});
    }

    public static class Signal {
        private byte[] bytes;

        public Signal(byte[] bytes) {
            this.bytes = Arrays.copyOf(bytes, bytes.length);
        }

        public byte[] getBytes() {
            return Arrays.copyOf(bytes, bytes.length);
        }
    }
}
