package org.arthan.desktop.tetris.controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.FigureOnScreen;
import org.arthan.desktop.tetris.model.GameScreen;

/**
 * Created by Arthur Shamsiev on 05.12.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class GameScreenController {

    public static final long ONE_SECOND = 1000000000;
    @FXML
    private GridPane gameGrid;
    private GameScreen gameScreen;

    public void initialize() {
        getGameScreen().fillWithSquares();
    }

    @FXML
    public void launchSquare() {
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);

        launch(square);
    }

    private void launch(FigureOnScreen shape) {
        updateGame(shape);
        final FigureOnScreen[] figure = {shape};
        final long[] start = {System.nanoTime()};
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - start[0]) / ONE_SECOND >= 1) {
                    figure[0] = figure[0].goDown();
                    updateGame(figure[0]);
                    start[0] += ONE_SECOND;

                    if (figure[0].isInTheBottom()) {
                        stop();
                    }
                }
            }
        }.start();
    }

    private void updateGame(FigureOnScreen figure) {
        getGameScreen().updateGame(figure);
    }

    public GameScreen getGameScreen() {
        if (gameScreen == null) {
            gameScreen = new GameScreen(gameGrid);
        }
        return gameScreen;
    }

    public void test_launchSquareNearBottom() {
        FigureOnScreen square_near_bottom = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_NEAR_BOTTOM);
        launch(square_near_bottom);
    }
}
