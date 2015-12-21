package org.arthan.desktop.tetris.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.model.MOVE;
import org.arthan.desktop.tetris.model.provider.FigureProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by Артур on 20.12.2015.
 * Next to Ufa.
 */
public class Game {

    static final long ONE_SECOND = TimeUnit.SECONDS.toNanos(1);
    private GameScreen innerGameScreen;

    private IntegerProperty speedProperty = new SimpleIntegerProperty(1);
    private ObjectProperty<MOVE> gameMoveProperty = new SimpleObjectProperty<>();
    long[] start = new long[1];

    public Game(GridPane gameGrid) {
        innerGameScreen = new GameScreen(gameGrid, null);

        getGameMoveProperty().set(MOVE.NONE);
    }

    GameScreen getGameScreen() {
        return innerGameScreen;
    }

    void launch(FigureProvider figureProvider) {
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

    void performMoveCommand() {
        switch (getGameMoveProperty().get()) {
            case RIGHT:
                getGameScreen().goRight();
                break;
            case LEFT:
                getGameScreen().goLeft();
                break;
            case ROTATE:
                getGameScreen().doRotate();
                break;
            case BOTTOM:
                getGameScreen().goBottom();
                break;
        }
        getGameMoveProperty().setValue(MOVE.NONE);
    }

    void performNextStep(long now) {
        long interval = ONE_SECOND / getSpeedProperty().get();
        boolean isTimeForNextStep = (now - start[0]) / interval >= 1;
        if (isTimeForNextStep) {
            getGameScreen().nextStep();
            start[0] += interval;
        }
    }

    void refreshStartTime() {
        start[0] = System.nanoTime();
    }

    void changeSpeed(int newSpeed) {
        getSpeedProperty().set(newSpeed);
        refreshStartTime();
    }

    public IntegerProperty getSpeedProperty() {
        return speedProperty;
    }

    public void setSpeedProperty(IntegerProperty speedProperty) {
        this.speedProperty = speedProperty;
    }

    public ObjectProperty<MOVE> getGameMoveProperty() {
        return gameMoveProperty;
    }

    public void setGameMoveProperty(ObjectProperty<MOVE> gameMoveProperty) {
        this.gameMoveProperty = gameMoveProperty;
    }
}
