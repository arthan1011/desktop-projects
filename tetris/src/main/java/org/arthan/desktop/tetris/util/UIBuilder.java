package org.arthan.desktop.tetris.util;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class UIBuilder {
    public static Parent createRoot() {
        GridPane mainScreen = new GridPane();
        mainScreen.setId("screen");
        mainScreen.setVgap(4);
        mainScreen.setHgap(4);
        fillWithSquares(mainScreen);

        return new BorderPane(mainScreen);
    }

    public static void fillWithSquares(GridPane mainScreen) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                mainScreen.add(createCell(), i, j);
            }
        }
    }

    public static Rectangle createCell() {
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(Color.LIGHTGREY);
        return rectangle;
    }
}
