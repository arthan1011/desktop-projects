package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.model.figure.FigureOnScreen;
import org.arthan.desktop.tetris.model.figure.Pixel;
import org.arthan.desktop.tetris.model.provider.FigureProvider;
import org.arthan.desktop.tetris.util.UIBuilder;

import java.util.*;
import java.util.stream.Collectors;

import static org.arthan.desktop.tetris.util.UIBuilder.createBlock;
import static org.arthan.desktop.tetris.util.UIBuilder.createGhostBlock;

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

        getGhost().getPixels().forEach((p) -> paintPixel(p, createGhostBlock()));
        figure.getPixels().forEach((p) -> paintPixel(p, createBlock()));
        blocks.forEach((p) -> paintPixel(p, createBlock()));
    }

    private FigureOnScreen getGhost() {
        FigureOnScreen ghost = figure;
        while (!figureArrived(ghost)) {
            ghost = ghost.goDown();
        }
        return ghost;
    }

    private void paintPixel(Pixel pixel, Rectangle newPixel) {
        Node child = getNodeAt(pixel.x, pixel.y);
        gameGrid.getChildren().remove(child);
        gameGrid.add(newPixel, pixel.x, pixel.y);
    }

    public void nextStep() {
        if (figure == null || figureArrived(figure)) {
            addFigureToBlocks();
            eraseFilledRows();
            Optional<FigureOnScreen> nextFigure = provider.next();
            if (nextFigure.isPresent()) {
                figure = nextFigure.get();
            }
        } else {
            figure = figure.goDown();
        }

        updateScreen();
    }

    // TODO: this method should be tested
    private void eraseFilledRows() {
        Collection<Pixel> blocksInFilledRows = findBlocksInFilledRows();
        if (blocksInFilledRows.isEmpty()) {
            return;
        }

        int highestYInFilledRows = blocksInFilledRows
                .stream()
                .mapToInt(p -> p.y)
                .max().getAsInt();
        int numberOfFilledRows = (int)blocksInFilledRows
                .stream()
                .mapToInt(p -> p.y)
                .distinct()
                .count();

        blocks.removeAll(blocksInFilledRows);

        List<Pixel> blocksAboveFilledRows = blocks.stream()
                .filter(p -> p.y < highestYInFilledRows)
                .collect(Collectors.toList());
        blocks.removeAll(blocksAboveFilledRows);
        blocks.addAll(blocksAboveFilledRows
                        .stream()
                        .map(pixel -> new Pixel(pixel.x, pixel.y + numberOfFilledRows))
                        .collect(Collectors.toList())
        );
    }

    private Collection<Pixel> findBlocksInFilledRows() {
        Map<Integer, List<Pixel>> pixelListMap = new HashMap<>();
        for (int i = 0; i < GAME_SCREEN_HEIGHT; i++) {
            pixelListMap.put(i, new ArrayList<>());
        }

        for (Pixel pixel : blocks) {
            pixelListMap.get(pixel.y).add(pixel);
        }

        Collection<Pixel> resultSet = new HashSet<>();
        Set<Integer> integers = pixelListMap.keySet();
        for (Integer rowNum : integers) {
            List<Pixel> rowPixels = pixelListMap.get(rowNum);
            if (rowPixels.size() == GAME_SCREEN_WIDTH) {
                resultSet.addAll(rowPixels);
            }
        }

        return resultSet;
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

    public void setBlocks(List<Pixel> blocksList) {
        blocks = Lists.newArrayList(blocksList);
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
        FigureOnScreen rotateFigure = figure.rotate();
        if (!figureCollides(rotateFigure)) {
            figure = rotateFigure;
            updateScreen();
        }
    }

    public boolean figureCollides(FigureOnScreen stickOnTop) {
        boolean figureIntersectsWithBlocks = stickOnTop.getPixels()
                .stream()
                .anyMatch(blocks::contains);
        boolean figureHasPixelsBeyondBoundaries = stickOnTop.getPixels()
                .stream()
                .anyMatch(p ->
                        (p.x < 0 || p.x > GAME_SCREEN_WIDTH - 1) ||
                        (p.y < 0 || p.y > GAME_SCREEN_HEIGHT - 1));

        return figureIntersectsWithBlocks || figureHasPixelsBeyondBoundaries;
    }
}
