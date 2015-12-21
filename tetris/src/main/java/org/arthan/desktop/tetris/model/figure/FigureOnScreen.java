package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.arthan.desktop.tetris.model.GameScreen;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ashamsiev on 09.12.2015
 */
public abstract class FigureOnScreen {

    private List<Pixel> currentPositionPixels;

    public List<Pixel> getPixels() {
        return Lists.newArrayList(currentPositionPixels);
    }

    public void setCurrentPositionPixels(List<Pixel> currentPositionPixels) {
        this.currentPositionPixels = Lists.newArrayList(currentPositionPixels);
    }

    public FigureOnScreen goDown() {
        List<Pixel> downPixels = Lists.newArrayList(currentPositionPixels);

        for (int i = 0; i < downPixels.size(); i++) {
            Pixel oldPixel = downPixels.get(i);
            downPixels.set(i, new Pixel(oldPixel.x, oldPixel.y + 1));
        }

        FigureOnScreen downFigure = createEmpty();
        downFigure.setCurrentPositionPixels(downPixels);
        return downFigure;
    }

    protected abstract FigureOnScreen createEmpty();

    public boolean isInTheBottom() {
        int lowestY = currentPositionPixels
                .stream()
                .mapToInt(p -> p.y)
                .max().getAsInt();

        return lowestY == GameScreen.GAME_SCREEN_HEIGHT - 1;
    }

    public List<Pixel> getPixelsUnderneath() {
        List<Pixel> pixels = findLowestPixels();
        return pixels.stream()
                .map(p -> new Pixel(p.x, p.y + 1))
                .collect(Collectors.toList());
    }

    public List<Pixel> findLowestPixels() {
        Map<Integer, Pixel> tempMap = Maps.newHashMap();
        currentPositionPixels.stream()
                .forEach(p -> {
                    if (tempMap.get(p.x) == null || tempMap.get(p.x).y < p.y) {
                        tempMap.put(p.x, p);
                    }
                });

        return Lists.newArrayList(tempMap.values());
    }

    public FigureOnScreen goRight() {
        if (!byRightBorder()) {
            List<Pixel> rightPixels = currentPositionPixels
                    .stream()
                    .map(pixel -> new Pixel(pixel.x + 1, pixel.y))
                    .collect(Collectors.toList());

            FigureOnScreen rightFigure = createEmpty();
            rightFigure.setCurrentPositionPixels(rightPixels);
            return rightFigure;
        } else {
            return this;
        }
    }

    private boolean byRightBorder() {
        int rightestX = currentPositionPixels
                .stream()
                .mapToInt(p -> p.x)
                .max().getAsInt();

        return rightestX == GameScreen.GAME_SCREEN_WIDTH - 1;
    }

    public FigureOnScreen goLeft() {
        if (!byLeftBorder()) {
            List<Pixel> leftPixels = currentPositionPixels
                    .stream()
                    .map(pixel -> new Pixel(pixel.x - 1, pixel.y))
                    .collect(Collectors.toList());

            FigureOnScreen leftFigure = createEmpty();
            leftFigure.setCurrentPositionPixels(leftPixels);
            return leftFigure;
        } else {
            return this;
        }
    }

    private boolean byLeftBorder() {
        int leftestX = currentPositionPixels
                .stream()
                .mapToInt(p -> p.x)
                .min().getAsInt();

        return leftestX == 0;
    }

    public abstract FigureOnScreen rotate();


    public static FigureOnScreen build(FigureOnScreen figure) {

        return null;
    }

    public FigureOnScreen make(FigureOnScreen figure) {
        FigureOnScreen newFigure = createEmpty();
        newFigure.setCurrentPositionPixels(figure.getPixels());
        return newFigure;
    }
}
