package org.arthan.desktop.reversi.model;

import java.util.Arrays;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class GameInfo {

    private byte[] gameInfo;

    public GameInfo(String gameInfoString) {
        gameInfo = gameInfoString.getBytes();
    }

    public GameInfo(byte[] bytesInfo) {
        gameInfo = bytesInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInfo gameInfo1 = (GameInfo) o;

        return Arrays.equals(gameInfo, gameInfo1.gameInfo);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(gameInfo);
    }

    @Override
    public String toString() {
        return "GameInfo{" +
                "gameInfo='" + new String(gameInfo) + '\'' +
                '}';
    }
}
