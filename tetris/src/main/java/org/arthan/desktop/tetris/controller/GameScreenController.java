package org.arthan.desktop.tetris.controller;

import com.google.common.collect.Lists;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.figure.Figure;
import org.arthan.desktop.tetris.model.figure.FigureOnScreen;
import org.arthan.desktop.tetris.model.MOVE;
import org.arthan.desktop.tetris.model.figure.Pixel;
import org.arthan.desktop.tetris.model.provider.FigureListProvider;
import org.arthan.desktop.tetris.model.provider.InfiniteFigureProvider;

/**
 * Created by Arthur Shamsiev on 05.12.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class GameScreenController {

    public static final int FASTEST_STEP_MILLIS = 100;

    @FXML
    public Label currentScoreLabel;
    @FXML
    private Label currentSpeedLabel;
    @FXML
    GridPane gameGrid;

    Game game;

    public void initialize() {
        game = new Game(gameGrid);
        currentSpeedLabel.textProperty().bind(game.speedProperty().asString());
        currentScoreLabel.textProperty().bind(game.scoreProperty().asString());

        gameGrid.getParent().getParent().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                doRotate();
            }
            if (event.getCode() == KeyCode.RIGHT) {
                goRight();
            }
            if (event.getCode() == KeyCode.LEFT) {
                goLeft();
            }
            if (event.getCode() == KeyCode.CONTROL) {
                goBottom();
            }

        });
    }

    public void launchSquare() {
        FigureOnScreen square = Figure.getSquareOnTop();
        game.launch(new InfiniteFigureProvider(square));
    }

    public void test_launchSquareNearBottom() {
        FigureOnScreen square_near_bottom = Figure.getTestSquareAbove2Bottom();
        game.launch(new FigureListProvider(square_near_bottom));
    }

    public void test_setBlocksInBottom() {
        game.getGameScreen().setBlocks(
                Lists.newArrayList(
                        new Pixel(0, 19),
                        new Pixel(2, 19),
                        new Pixel(4, 19),
                        new Pixel(6, 19),
                        new Pixel(8, 19))
        );
    }

    public void test_launch2Squares3PixelAboveBottom() {
        game.launch(new FigureListProvider(
                Figure.getTestSquareAbove3Bottom(),
                Figure.getTestSquareAbove3Bottom()
        ));
    }

    public void testLaunchSquare2PixelAboveBottom__withFigureProvider() {
        game.launch(new FigureListProvider(
                Figure.getTestSquareAbove2Bottom(),
                Figure.getSquareOnTop()
        ));
    }

    public void test_setSpeed5() {
        game.changeSpeed(5);
    }

    public void test_setSpeed2() {
        game.changeSpeed(2);
    }

    public void test_launch_three_squares_3_pixel_above_bottom() {
        game.launch(new FigureListProvider(
                Figure.getTestSquareAbove3Bottom(),
                Figure.getTestSquareAbove3Bottom(),
                Figure.getTestSquareAbove3Bottom()
        ));
    }

    private void setGameMove(MOVE move) {
        game.getGameMoveProperty().setValue(move);
    }

    public void goRight() {
        setGameMove(MOVE.RIGHT);
    }

    public void goLeft() {
        setGameMove(MOVE.LEFT);
    }

    public void test_launch_two_squares_on_top() {
        launchSquare();
    }

    public void goBottom() {
        setGameMove(MOVE.BOTTOM);
    }

    public void test_launchStick() {
        game.launch(new FigureListProvider(
                Figure.getStickOnTop()
        ));
    }

    public void doRotate() {
        setGameMove(MOVE.ROTATE);
    }

    public void test_launch_L_figure() {
        game.launch(new FigureListProvider(
                Figure.getLFigureOnTop()
        ));
    }

    public void test_launch3SticksNearBoundaries() {
        game.launch(new FigureListProvider(
                Figure.getStickNearLeftOnTop(),
                Figure.getStickNearRightOnTop(),
                Figure.getStickAboveBottom()
        ));
    }

    public void test_setBlocksOnRightSide() {
        game.getGameScreen().setBlocks(
                Lists.newArrayList(
                        new Pixel(9, 0),
                        new Pixel(9, 1),
                        new Pixel(9, 2),
                        new Pixel(9, 3),
                        new Pixel(9, 4),
                        new Pixel(9, 5),
                        new Pixel(9, 6),
                        new Pixel(8, 19))
        );
    }

    public void test_set_blocks_for_erasure() {
        game.getGameScreen().setBlocks(
                Lists.newArrayList(
                        new Pixel(0, 14), new Pixel(2, 14), new Pixel(3, 14), new Pixel(6, 14), new Pixel(7, 14), new Pixel(8, 14), new Pixel(9, 14),
                        new Pixel(0, 15), new Pixel(2, 15), new Pixel(3, 15), new Pixel(6, 15), new Pixel(7, 15), new Pixel(8, 15), new Pixel(9, 15),
                        new Pixel(0, 16), new Pixel(2, 16), new Pixel(3, 16), new Pixel(6, 16), new Pixel(7, 16), new Pixel(8, 16), new Pixel(9, 16),
                        new Pixel(0, 17), new Pixel(1, 17), new Pixel(2, 17), new Pixel(3, 17), new Pixel(6, 17), new Pixel(7, 17), new Pixel(8, 17), new Pixel(9, 17),
                        new Pixel(0, 18), new Pixel(1, 18), new Pixel(2, 18), new Pixel(3, 18), new Pixel(4, 18), new Pixel(5, 18), new Pixel(6, 18), new Pixel(7, 18), new Pixel(9, 18),
                        new Pixel(0, 19), new Pixel(1, 19), new Pixel(2, 19), new Pixel(3, 19), new Pixel(4, 19), new Pixel(5, 19), new Pixel(6, 19), new Pixel(7, 19), new Pixel(9, 19)
                )
        );
    }

    public void launchInfinite() {
        game.launch(new InfiniteFigureProvider());
    }

    public void test_set_blocks_on_top() {
        game.getGameScreen().setBlocks(
                Lists.newArrayList(
                        new Pixel(6, 1),
                        new Pixel(6, 2),
                        new Pixel(6, 3)
                )
        );
    }

    public void test_set_blocks_for_score_gain() {
        game.getGameScreen().setBlocks(
                Lists.newArrayList(
                        new Pixel(0, 18), new Pixel(1, 18), new Pixel(2, 18), new Pixel(3, 18), new Pixel(6, 18), new Pixel(7, 18), new Pixel(8, 18), new Pixel(9, 18),
                        new Pixel(0, 19), new Pixel(1, 19), new Pixel(2, 19), new Pixel(3, 19), new Pixel(6, 19), new Pixel(7, 19), new Pixel(8, 19), new Pixel(9, 19)
                )
        );
    }
}
