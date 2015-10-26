package org.arthan.desktop.reversi.model;

import java.util.Arrays;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class GameInfo {

    private byte[] gameInfo;

    public GameInfo(String gameInfoString) {
        gameInfo = new byte[65];

        int byteIndex = 0;
        for (char c : gameInfoString.toCharArray()) {
            gameInfo[byteIndex++] = (byte) Character.getNumericValue(c);
        }
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
        return "GameInfo{gameInfo=" + getBoardString() + '}';
    }

    private String getBoardString() {
        StringBuilder result = new StringBuilder("\n");
        for (int i = 1; i < gameInfo.length + 1; i++) {
            byte cell = gameInfo[i-1];
            if (i == 65) {
                result.append(cell == 1 ? "BLACK" : "WHITE");
                break;
            }
            if (cell == 0)
                result.append("-");
            if (cell == 1)
                result.append("1");
            if (cell == 2)
                result.append("2");
            if (i % 8 == 0)
                result.append("\n");
            else
                result.append(" ");
        }
        return result.toString();
    }

    public byte[] getBytes() {
        return gameInfo;
    }
}
