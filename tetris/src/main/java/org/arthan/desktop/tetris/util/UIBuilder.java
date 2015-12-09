package org.arthan.desktop.tetris.util;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.model.GameScreen;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class UIBuilder {

    public static Parent createGameScreen() {
        return UILoader.loadGameScreen();
    }

    public static Parent createTitleScreen() {
        return UILoader.loadTitleScreen();
    }

    public static Parent createRoot() {
        Pane root = new Pane();
        root.setId("root");
        root.getChildren().add(UIBuilder.createTitleScreen());
        return root;
    }

    public static Rectangle createPixel() {
        Rectangle rect = new Rectangle(20, 20);
        rect.setFill(Color.RED);
        return rect;
    }

    public static Rectangle createCell() {
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(Color.LIGHTGREY);
        return rectangle;
    }
}
