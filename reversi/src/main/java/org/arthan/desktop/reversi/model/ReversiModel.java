package org.arthan.desktop.reversi.model;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableBooleanValue;

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
        initBoard();
    }

    private void initBoard() {
        int center_1 = BOARD_SIZE / 2 - 1;
        int center_2 = BOARD_SIZE / 2;

        board[center_1][center_1].setValue(OWNER.WHITE);
        board[center_1][center_2].setValue(OWNER.BLACK);
        board[center_2][center_1].setValue(OWNER.BLACK);
        board[center_2][center_2].setValue(OWNER.WHITE);
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
                while (x >=0 && x < BOARD_SIZE && y >=0 && y < BOARD_SIZE) {
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
        initBoard();
        turn.setValue(OWNER.BLACK);
    }

    private static class ReversiModelHolder {
        private static final ReversiModel INSTANCE = new ReversiModel();
    }
}
