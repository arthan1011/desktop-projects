package org.arthan.desktop.tetris.controller;

import javafx.concurrent.Task;
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

    @FXML
    private GridPane gameGrid;
    private GameScreen gameScreen;

    public void initialize() {
        getGameScreen().fillWithSquares();
    }

    @FXML
    public void launchSquare() {
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);
        updateGame(square);

        Task<FigureOnScreen> task =  new Task<FigureOnScreen>() {
            @Override
            protected FigureOnScreen call() throws Exception {
                FigureOnScreen figure = square;
                long startTime = System.nanoTime();
                while (figure.getPixels()[0].y < 20) {
                    if ((System.nanoTime() / 1000000000.0) - (startTime / 1000000000.0) > 1.5) {
                        figure = figure.goDown();
                        break;
                    }
                }
                return figure;
            }
        };

        task.setOnSucceeded(event -> {
            updateGame(task.getValue());
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
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
}
