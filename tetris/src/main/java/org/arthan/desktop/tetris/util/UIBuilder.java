package org.arthan.desktop.tetris.util;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class UIBuilder {

    public static final int GAME_SCREEN_WIDTH = 10;
    public static final int GAME_SCREEN_HEIGHT = 20;

    public static Parent createGameScreen() {
        BorderPane gameScreen = UILoader.loadGameScreen();

        GridPane gameGrid = (GridPane) gameScreen.getCenter();
        fillWithSquares(gameGrid);

        return gameScreen;
    }

    public static Parent createTitleScreen() {
        return UILoader.loadTitleScreen();
    }

    public static void fillWithSquares(GridPane mainScreen) {
        for (int i = 0; i < GAME_SCREEN_WIDTH; i++) {
            for (int j = 0; j < GAME_SCREEN_HEIGHT; j++) {
                mainScreen.add(createCell(), i, j);
                if (i == 1 && j == 4) {
                    Rectangle rectangle = new Rectangle(20, 20);
                    rectangle.setFill(Color.RED);
                    mainScreen.add(rectangle, i, j);
                }
            }
        }
    }

    public static Rectangle createCell() {
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(Color.LIGHTGREY);
        return rectangle;
    }

    public static Parent createRoot() {
        Pane root = new Pane();
        root.setId("root");
        root.getChildren().add(UIBuilder.createTitleScreen());
        return root;
    }
}
