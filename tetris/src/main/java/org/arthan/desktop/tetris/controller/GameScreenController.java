package org.arthan.desktop.tetris.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.FigureOnScreen;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.model.Pixel;

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
        getGameScreen().fillWithPixels();
    }

    @FXML
    public void launchSquare() {
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);
        launch(square);
    }

    private void launch(FigureOnScreen shape) {
        getGameScreen().setFigure(shape);
        final long[] start = {System.nanoTime()};
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - start[0]) / ONE_SECOND >= 1) {
                    getGameScreen().figureDown();
                    start[0] += ONE_SECOND;

                    if (getGameScreen().figureReachedBottom() || getGameScreen().figureReachedBlocks()) {
                        stop();
                    }
                }
            }
        }.start();
    }

    public GameScreen getGameScreen() {
        if (gameScreen == null) {
            gameScreen = new GameScreen(gameGrid);
        }
        return gameScreen;
    }

    public void test_launchSquareNearBottom() {
        FigureOnScreen square_near_bottom = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        launch(square_near_bottom);
    }

    public void test_setBlocksInBottom() {
        getGameScreen().setBlocks(
                new Pixel[]{
                        new Pixel(0, 19),
                        new Pixel(2, 19),
                        new Pixel(4, 19),
                        new Pixel(6, 19),
                        new Pixel(8, 19),
                }
        );
    }
}
