package org.arthan.desktop.reversi.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ashamsiev on 07.10.2015
 */
public class ReversiModel {

    public static final int BOARD_SIZE = 8;

    public ObjectProperty<OWNER> turn = new SimpleObjectProperty<>(OWNER.BLACK);
    @SuppressWarnings("unchecked")
    public ObjectProperty<OWNER>[][] board = new ObjectProperty[BOARD_SIZE][BOARD_SIZE];

    public static ReversiModel getInstance() {
        return ReversiModelHolder.INSTANCE;
    }

    private ReversiModel() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new SimpleObjectProperty<>(OWNER.NONE);
            }
        }
        refresh();
    }

    private void refresh() {
        populate(retrieveBoardInfo());
    }

    private byte[] retrieveBoardInfo() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/wild/rest/reversi");
        Response response = target.request().get();
        InputStream inputStream = response.readEntity(InputStream.class);

        byte[] bytes = new byte[response.getLength()];
        try {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public void populate(byte[] boardArray) {
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            int x = boardArray[i*3];
            int y = boardArray[i*3 + 1];
            byte ownerCode = boardArray[i*3 + 2];
            OWNER owner = ownerCode == 0 ? OWNER.NONE : ownerCode == 1 ? OWNER.BLACK : OWNER.WHITE;
            board[x][y].setValue(owner);
        }
    }


    public NumberExpression getScore(OWNER owner) {
        NumberExpression score = new SimpleIntegerProperty(0);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                score = score.add(Bindings.when(board[i][j].isEqualTo(owner))
                        .then(1)
                        .otherwise(0));
            }
        }
        return score;
    }

    public NumberExpression getTurnsRemaining(OWNER owner) {
        NumberExpression emptyCellCount = getScore(OWNER.NONE);
        return Bindings.when(turn.isEqualTo(owner))
                .then(emptyCellCount.add(1).divide(2))
                .otherwise(emptyCellCount.divide(2));
    }

    public BooleanBinding legalMove(int x, int y) {

        return board[x][y].isEqualTo(OWNER.NONE).and(
                    canFlip(x, y, 1, 1).or(
                    canFlip(x, y, 1, -1).or(
                    canFlip(x, y, -1, 1).or(
                    canFlip(x, y, -1, -1).or(
                    canFlip(x, y, 1, 0).or(
                    canFlip(x, y, 0, -1).or(
                    canFlip(x, y, 0, 1).or(
                    canFlip(x, y, -1, 0))))))))
                );
    }

    private BooleanBinding canFlip(final int xCoord, final int yCoord, final int dirX, final int dirY) {
        return new BooleanBinding() {

            {
                bind(turn);
            }

            @Override
            protected boolean computeValue() {
                int x = xCoord + dirX;
                int y = yCoord + dirY;
                if (!(x >=0 && x < BOARD_SIZE && y >=0 && y < BOARD_SIZE)) {
                    return false;
                }


                boolean adjacentCellIsOppositeColor = board[x][y].get() == turn.get().opposite();
                if (!adjacentCellIsOppositeColor) {
                    return false;
                }
                while (x > 0 && x < BOARD_SIZE-1 && y > 0 && y < BOARD_SIZE-1) {
                    x += dirX;
                    y += dirY;
                    if (board[x][y].get() == OWNER.NONE) {
                        return false;
                    }
                    if (board[x][y].get() == turn.get()) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public void restart() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].setValue(OWNER.NONE);
            }
        }
        turn.setValue(OWNER.BLACK);
    }

    public void play(int x, int y) {
        if (legalMove(x, y).get()) {
            board[x][y].setValue(turn.get());
            flip(x, y, 1, 1);
            flip(x, y, 1, -1);
            flip(x, y, -1, 1);
            flip(x, y, -1, -1);
            flip(x, y, 1, 0);
            flip(x, y, 0, -1);
            flip(x, y, 0, 1);
            flip(x, y, -1, 0);
            turn.setValue(turn.get().opposite());
        }
    }

    private void flip(int xCoord, int yCoord, int dirX, int dirY) {
        if (canFlip(xCoord, yCoord, dirX, dirY).get()) {
            int x = xCoord + dirX;
            int y = yCoord + dirY;
            while (x >=0 && x < BOARD_SIZE && y >=0 && y < BOARD_SIZE && board[x][y].get() != turn.get()) {
                board[x][y].setValue(turn.get());
                x += dirX;
                y += dirY;
            }
        }
    }

    private static class ReversiModelHolder {
        private static final ReversiModel INSTANCE = new ReversiModel();
    }
}
