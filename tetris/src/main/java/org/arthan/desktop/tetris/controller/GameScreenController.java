package org.arthan.desktop.tetris.controller;

import com.google.common.collect.Lists;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.FigureOnScreen;
import org.arthan.desktop.tetris.model.GAME_STATUS;
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

    private void launch(FigureOnScreen figure) {
        getGameScreen().setFigure(figure);
        final long[] start = {System.nanoTime()};
        // TODO: animationTimer should not be stopped and started for every new figure. Instead it should use figure provider
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - start[0]) / ONE_SECOND >= 1) {
                    GAME_STATUS status = getGameScreen().figureDown();
                    start[0] += ONE_SECOND;

                    if (status == GAME_STATUS.STOPPED) {
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
                Lists.newArrayList(
                        new Pixel(0, 19),
                        new Pixel(2, 19),
                        new Pixel(4, 19),
                        new Pixel(6, 19),
                        new Pixel(8, 19))
        );
    }

    public void test_launchSquare3PixelAboveBottom() {
        FigureOnScreen square3PixelAboveBottom = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM);
        launch(square3PixelAboveBottom);
    }

    public void testLaunchSquare2PixelAboveBottom__withFigureProvider() {

    }
}
