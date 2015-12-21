package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.model.figure.FigureOnScreen;
import org.arthan.desktop.tetris.model.figure.Pixel;
import org.arthan.desktop.tetris.model.provider.FigureProvider;
import org.arthan.desktop.tetris.util.UIBuilder;

import java.util.List;
import java.util.Optional;

import static org.arthan.desktop.tetris.util.UIBuilder.createPixel;

/**
 * Created by ashamsiev on 09.12.2015
 * This class is state of the current game
 */
public class GameScreen {

    public static final int GAME_SCREEN_WIDTH = 10;
    public static final int GAME_SCREEN_HEIGHT = 20;
    private final GridPane gameGrid;
    private List<Pixel> blocks = Lists.newArrayList();
    private FigureOnScreen figure;
    private FigureProvider provider;

    public GameScreen(GridPane gameGrid, FigureProvider figureProvider) {
        this.gameGrid = gameGrid;
        this.provider = figureProvider;
    }

    private void fillWithPixels() {
        for (int i = 0; i < GAME_SCREEN_WIDTH; i++) {
            for (int j = 0; j < GAME_SCREEN_HEIGHT; j++) {
                gameGrid.add(UIBuilder.createCell(), i, j);
            }
        }
    }

    private Node getNodeAt(int x, int y) {
        ObservableList<Node> children = gameGrid.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == y && GridPane.getColumnIndex(node) == x) {
                return node;
            }
        }
        return null;
    }

    private void removeChildren() {
        ObservableList<Node> children = gameGrid.getChildren();
        children.clear();
    }

    private void addFigureToBlocks() {
        if (figure != null) {
            blocks.addAll(figure.getPixels());
        }
    }

    private boolean figureArrived(FigureOnScreen figure) {
        return figureReachedBlocks(figure) || figure.isInTheBottom();
    }

    private void updateScreen() {
        removeChildren();
        fillWithPixels();

        figure.getPixels().forEach(this::paintPixel);
        blocks.forEach(this::paintPixel);
    }

    private void paintPixel(Pixel pixel) {
        Node child = getNodeAt(pixel.x, pixel.y);
        gameGrid.getChildren().remove(child);
        gameGrid.add(createPixel(), pixel.x, pixel.y);
    }

    public void nextStep() {
        if (figure == null || figureArrived(figure)) {
            addFigureToBlocks();
            Optional<FigureOnScreen> nextFigure = provider.next();
            if (nextFigure.isPresent()) {
                figure = nextFigure.get();
            } else {
                return;
            }
        } else {
            figure = figure.goDown();
        }

        updateScreen();
    }

    private boolean figureReachedBlocks(FigureOnScreen figure) {
        List<Pixel> pixelsUnderneath = figure.getPixelsUnderneath();
        for (Pixel pixel : pixelsUnderneath) {
            if (blocks.contains(pixel)) {
                return true;
            }
        }
        return false;
    }

    boolean figureReachedBlocks() {
        return figureReachedBlocks(this.figure);
    }

    List<Pixel> getGameData() {
        List<Pixel> resultList = Lists.newArrayList();
        resultList.addAll(blocks);
        resultList.addAll(figure.getPixels());
        return resultList;
    }

    public List<Pixel> getBlocks() {
        return Lists.newArrayList(blocks);
    }

    public void setBlocks(List<Pixel> blocksInBottom) {
        blocks = Lists.newArrayList(blocksInBottom);
    }

    public void setProvider(FigureProvider provider) {
        this.provider = provider;
    }

    public void goRight() {
        figure = figure.goRight();
        updateScreen();
    }

    public void goLeft() {
        figure = figure.goLeft();
        updateScreen();
    }

    public void goBottom() {
        figure = getFallenFigure();
        updateScreen();
    }

    private FigureOnScreen getFallenFigure() {
        FigureOnScreen fallenFigure = figure;

        while (!figureArrived(fallenFigure)) {
            fallenFigure = fallenFigure.goDown();
        }

        return fallenFigure;
    }

    public void doRotate() {
        figure = figure.rotate();
        updateScreen();
    }
}
