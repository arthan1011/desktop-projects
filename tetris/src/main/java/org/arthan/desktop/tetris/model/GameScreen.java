package org.arthan.desktop.tetris.model;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang.ArrayUtils;
import org.arthan.desktop.tetris.util.UIBuilder;

import java.util.Arrays;

import static org.arthan.desktop.tetris.util.UIBuilder.createPixel;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class GameScreen {

    public static final int GAME_SCREEN_WIDTH = 10;
    public static final int GAME_SCREEN_HEIGHT = 20;
    public final GridPane gameGrid;
    private Pixel[] blocks = new Pixel[0];
    private FigureOnScreen figure;

    public GameScreen(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void fillWithPixels() {
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

    public void updateGame() {
        removeChildren();
        fillWithPixels();
        for (Pixel pixel : figure.getPixels()) {
            Node child = getNodeAt(pixel.x, pixel.y);
            gameGrid.getChildren().remove(child);
            gameGrid.add(createPixel(), pixel.x, pixel.y);
        }

        for (Pixel block : blocks) {
            Node child = getNodeAt(block.x, block.y);
            gameGrid.getChildren().remove(child);
            gameGrid.add(createPixel(), block.x, block.y);
        }
    }

    public void setBlocks(Pixel[] blocksInBottom) {
        blocks = Arrays.copyOf(blocksInBottom, blocksInBottom.length);
    }

    public Pixel[] getPixelArray() {
        return (Pixel[]) ArrayUtils.addAll(blocks, figure.getPixels());
    }

    public void setFigure(FigureOnScreen figure) {
        this.figure = figure;
        updateGame();
    }

    public void figureDown() {
        figure = figure.goDown();
        updateGame();
    }

    public boolean figureReachedBottom() {
        return figure.isInTheBottom();
    }

    public boolean figureReachedBlocks() {
        Pixel[] pixelsUnderneath = figure.getPixelsUnderneath();
        for (Pixel pixel : pixelsUnderneath) {
            if (ArrayUtils.contains(blocks, pixel)) {
                return true;
            }
        }
        return false;
    }
}
