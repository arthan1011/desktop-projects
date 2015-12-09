package org.arthan.desktop.tetris.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.util.UIBuilder;

import static org.arthan.desktop.tetris.util.UIBuilder.createPixel;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class GameScreen {

    public static final int GAME_SCREEN_WIDTH = 10;
    public static final int GAME_SCREEN_HEIGHT = 20;
    public final GridPane gameGrid;

    public GameScreen(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void fillWithSquares() {
        for (int i = 0; i < GAME_SCREEN_WIDTH; i++) {
            for (int j = 0; j < GAME_SCREEN_HEIGHT; j++) {
                gameGrid.add(UIBuilder.createCell(), i, j);
            }
        }
    }

    public Node getNodeAt(int x, int y) {
        ObservableList<Node> children = gameGrid.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                return node;
            }
        }
        return null;
    }

    public void removeChildren() {
        ObservableList<Node> children = gameGrid.getChildren();
        children.clear();
    }

    public void updateGame(FigureOnScreen figure) {
        removeChildren();
        fillWithSquares();
        for (Pixel pixel : figure.getPixels()) {
            Node child = getNodeAt(pixel.x, pixel.y);
            gameGrid.getChildren().remove(child);
            gameGrid.add(createPixel(), pixel.x, pixel.y);
        }
    }
}
