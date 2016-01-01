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
 * Created by Artur on 20.12.2015.
 * Next to Ufa.
 */
public class Game {

    static final long ONE_SECOND = TimeUnit.SECONDS.toNanos(1);
    private GameScreen innerGameScreen;

    private IntegerProperty speed = new SimpleIntegerProperty(1);
    private IntegerProperty score = new SimpleIntegerProperty(0);
    private ObjectProperty<MOVE> gameMoveProperty = new SimpleObjectProperty<>();
    long startTime;

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
                refreshStartTime();
                break;
        }
        getGameMoveProperty().setValue(MOVE.NONE);
    }

    void performNextStep(long now) {
        long interval = ONE_SECOND / getSpeed();
        boolean isTimeForNextStep = (now - startTime) / interval >= 1;
        if (isTimeForNextStep) {
            getGameScreen().nextStep();
            startTime += interval;
        }

        updateScore();
    }

    private void updateScore() {
        setScore(getGameScreen().getScore());
    }

    void refreshStartTime() {
        startTime = System.nanoTime();
    }

    void changeSpeed(int newSpeed) {
        setSpeed(newSpeed);
        refreshStartTime();
    }

    public ObjectProperty<MOVE> getGameMoveProperty() {
        return gameMoveProperty;
    }

    public void setGameMoveProperty(ObjectProperty<MOVE> gameMoveProperty) {
        this.gameMoveProperty = gameMoveProperty;
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getSpeed() {
        return speed.get();
    }

    public IntegerProperty speedProperty() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }
}
