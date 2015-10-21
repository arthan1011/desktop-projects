package org.arthan.desktop.reversi.server;

import org.arthan.desktop.reversi.model.OWNER;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class ServerModel {

    public static final int BOARD_SIZE = 8;

    public OWNER turn = OWNER.BLACK;
    @SuppressWarnings("unchecked")
    public OWNER[][] board = new OWNER[BOARD_SIZE][BOARD_SIZE];

    public static ServerModel getInstance() {
        return ReversiModelHolder.INSTANCE;
    }

    private ServerModel() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = OWNER.NONE;
            }
        }
        initBoard();
    }

    private void initBoard() {
        int center_1 = BOARD_SIZE / 2 - 1;
        int center_2 = BOARD_SIZE / 2;

        board[center_1][center_1] = OWNER.WHITE;
        board[center_1][center_2] = OWNER.BLACK;
        board[center_2][center_1] = OWNER.BLACK;
        board[center_2][center_2] = OWNER.WHITE;
    }

    private boolean canFlip(final int xCoord, final int yCoord, final int dirX, final int dirY) {
        int x = xCoord + dirX;
        int y = yCoord + dirY;
        if (!(x >=0 && x < BOARD_SIZE && y >=0 && y < BOARD_SIZE)) {
            return false;
        }


        boolean adjacentCellIsOppositeColor = board[x][y] == turn.opposite();
        if (!adjacentCellIsOppositeColor) {
            return false;
        }
        while (x > 0 && x < BOARD_SIZE-1 && y > 0 && y < BOARD_SIZE-1) {
            x += dirX;
            y += dirY;
            if (board[x][y] == OWNER.NONE) {
                return false;
            }
            if (board[x][y] == turn) {
                return true;
            }
        }
        return false;
    }

    public void restart() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = OWNER.NONE;
            }
        }
        initBoard();
        turn = OWNER.BLACK;
    }

    public void play(int x, int y) {
        board[x][y] = turn;
        flip(x, y, 1, 1);
        flip(x, y, 1, -1);
        flip(x, y, -1, 1);
        flip(x, y, -1, -1);
        flip(x, y, 1, 0);
        flip(x, y, 0, -1);
        flip(x, y, 0, 1);
        flip(x, y, -1, 0);
        turn = turn.opposite();
    }

    private void flip(int xCoord, int yCoord, int dirX, int dirY) {
        if (canFlip(xCoord, yCoord, dirX, dirY)) {
            int x = xCoord + dirX;
            int y = yCoord + dirY;
            while (x >=0 && x < BOARD_SIZE && y >=0 && y < BOARD_SIZE && board[x][y] != turn) {
                board[x][y] = turn;
                x += dirX;
                y += dirY;
            }
        }
    }


    public byte[] getBoardInfo() {
        byte[] result = new byte[65];

        int byteIndex = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                OWNER owner = board[i][j];
                result[byteIndex++] = (byte) (owner == OWNER.NONE ? 0 : owner == OWNER.BLACK ? 1 : 2);
            }
        }
        result[byteIndex] = turn.getCode();
        return result;
    }

    private static class ReversiModelHolder {
        private static final ServerModel INSTANCE = new ServerModel();
    }
}
