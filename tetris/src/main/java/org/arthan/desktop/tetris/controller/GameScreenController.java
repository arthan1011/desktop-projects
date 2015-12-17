package org.arthan.desktop.tetris.controller;

import com.google.common.collect.Lists;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.FigureOnScreen;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.model.Pixel;
import org.arthan.desktop.tetris.model.provider.FigureListProvider;
import org.arthan.desktop.tetris.model.provider.FigureProvider;
import org.arthan.desktop.tetris.model.provider.InfiniteFigureProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by Arthur Shamsiev on 05.12.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class GameScreenController {

    private static final long ONE_SECOND = TimeUnit.SECONDS.toNanos(1);
    @FXML
    private GridPane gameGrid;
    private GameScreen gameScreen;

    public void launchSquare() {
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);
        launch(new InfiniteFigureProvider(square));
    }

    private void launch(FigureProvider figureProvider) {
        getGameScreen().setProvider(figureProvider);
        getGameScreen().nextStep();
        final long[] start = {System.nanoTime()};
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - start[0]) / ONE_SECOND >= 1) {
                    getGameScreen().nextStep();
                    start[0] += ONE_SECOND;
                }
            }
        }.start();
    }

    private GameScreen getGameScreen() {
        if (gameScreen == null) {
            gameScreen = new GameScreen(gameGrid, null);
        }
        return gameScreen;
    }

    public void test_launchSquareNearBottom() {
        FigureOnScreen square_near_bottom = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        launch(new FigureListProvider(square_near_bottom));
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

    public void test_launch2Squares3PixelAboveBottom() {
        launch(new FigureListProvider(
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM),
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM)
        ));
    }

    public void testLaunchSquare2PixelAboveBottom__withFigureProvider() {
        launch(new FigureListProvider(
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM),
                new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP)
        ));
    }
}
