package org.arthan.desktop.tetris.controller;

import com.google.common.collect.Lists;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.FigureOnScreen;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.model.MOVE_DIRECTION;
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
    public static final int FASTEST_STEP_MILLIS = 100;

    @FXML
    private Label currentSpeedLabel;
    @FXML
    private GridPane gameGrid;

    private GameScreen gameScreen;
    private IntegerProperty speedProperty = new SimpleIntegerProperty(1);
    private ObjectProperty<MOVE_DIRECTION> moveProperty = new SimpleObjectProperty<>();
    private long[] start = new long[1];

    public void initialize() {
        currentSpeedLabel.textProperty().bind(speedProperty.asString());
        moveProperty.set(MOVE_DIRECTION.NONE);
    }

    public void launchSquare() {
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);
        launch(new InfiniteFigureProvider(square));
    }

    private void launch(FigureProvider figureProvider) {
        getGameScreen().setProvider(figureProvider);
        getGameScreen().nextStep();
        refreshStartTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                performMoveCommand();
                performNextStep(now);
            }
        }.start();
    }

    private void performNextStep(long now) {
        long interval = ONE_SECOND / speedProperty.get();
        boolean isTimeForNextStep = (now - start[0]) / interval >= 1;
        if (isTimeForNextStep) {
            getGameScreen().nextStep();
            start[0] += interval;
        }
    }

    private void performMoveCommand() {
        switch (moveProperty.get()) {
            case RIGHT:
                getGameScreen().goRight();
                break;
            case LEFT:
                getGameScreen().goLeft();
        }
        moveProperty.setValue(MOVE_DIRECTION.NONE);
    }

    private void refreshStartTime() {
        start[0] = System.nanoTime();
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

    public void test_setSpeed5() {
        speedProperty.set(5);
        refreshStartTime();
    }

    public void test_setSpeed2() {
        speedProperty.set(2);
        refreshStartTime();
    }

    public void test_launch_three_squares_3_pixel_above_bottom() {
        launch(new FigureListProvider(
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM),
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM),
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_3_BOTTOM)
        ));
    }

    public void goRight() {
        moveProperty.setValue(MOVE_DIRECTION.RIGHT);
    }

    public void goLeft() {
        moveProperty.setValue(MOVE_DIRECTION.LEFT);
    }
}
