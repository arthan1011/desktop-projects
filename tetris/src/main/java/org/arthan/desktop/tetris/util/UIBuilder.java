package org.arthan.desktop.tetris.util;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class UIBuilder {

    public static final Color BLOCK_COLOR = Color.RED;
    public static final Color CELL_COLOR = Color.LIGHTGREY;
    public static final Color GHOST_COLOR = Color.LIGHTYELLOW;

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

    public static Rectangle createBlock() {
        return makePixel(BLOCK_COLOR);
    }

    public static Rectangle createCell() {
        return makePixel(CELL_COLOR);
    }

    public static Rectangle createGhost() {
        return makePixel(GHOST_COLOR);
    }

    private static Rectangle makePixel(Color ghostColor) {
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(ghostColor);
        return rectangle;
    }
}
