package org.arthan.desktop.tetris.controller;

import com.google.common.collect.Lists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private Label currentSpeedLabel;
    @FXML
    GridPane gameGrid;

    Game game;

    public void initialize() {
        game = new Game(gameGrid);
        currentSpeedLabel.textProperty().bind(game.getSpeedProperty().asString());
    }

    public void launchSquare() {
        FigureOnScreen square = Figure.SQUARE_ON_TOP;
        game.launch(new InfiniteFigureProvider(square));
    }

    public void test_launchSquareNearBottom() {
        FigureOnScreen square_near_bottom = Figure.TEST_SQUARE_ABOVE_2_BOTTOM;
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
                Figure.TEST_SQUARE_ABOVE_3_BOTTOM,
                Figure.TEST_SQUARE_ABOVE_3_BOTTOM
        ));
    }

    public void testLaunchSquare2PixelAboveBottom__withFigureProvider() {
        game.launch(new FigureListProvider(
                Figure.TEST_SQUARE_ABOVE_2_BOTTOM,
                Figure.SQUARE_ON_TOP
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
                Figure.TEST_SQUARE_ABOVE_3_BOTTOM,
                Figure.TEST_SQUARE_ABOVE_3_BOTTOM,
                Figure.TEST_SQUARE_ABOVE_3_BOTTOM
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
                Figure.STICK_ON_TOP
        ));
    }

    public void doRotate() {
        setGameMove(MOVE.ROTATE);
    }

    public void test_launch_L_figure() {

    }
}
