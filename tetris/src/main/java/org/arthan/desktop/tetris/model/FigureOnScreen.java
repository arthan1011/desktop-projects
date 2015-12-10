package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class FigureOnScreen {

    public static final List<Pixel> SQUARE_ON_TOP = Lists.newArrayList(
            new Pixel(4, 0),
            new Pixel(5, 0),
            new Pixel(4, 1),
            new Pixel(5, 1)
    );
    public static final List<Pixel> TEST_SQUARE_ABOVE_2_BOTTOM = Lists.newArrayList(
            new Pixel(4, 16),
            new Pixel(5, 16),
            new Pixel(4, 17),
            new Pixel(5, 17)
    );
    public static final List<Pixel> TEST_SQUARE_ABOVE_1_BOTTOM = Lists.newArrayList(
            new Pixel(4, 17),
            new Pixel(5, 17),
            new Pixel(4, 18),
            new Pixel(5, 18)
    );
    public static final List<Pixel> TEST_SQUARE_ABOVE_3_BOTTOM = Lists.newArrayList(
            new Pixel(4, 15),
            new Pixel(5, 15),
            new Pixel(4, 16),
            new Pixel(5, 16)
    );

    private List<Pixel> pixels;

    public FigureOnScreen(List<Pixel> pixels) {
        this.pixels = Lists.newArrayList(pixels);
    }

    public List<Pixel> getPixels() {
        return Lists.newArrayList(pixels);
    }

    public FigureOnScreen goDown() {
        FigureOnScreen downFigure = new FigureOnScreen(pixels);
        List<Pixel> pixels = downFigure.pixels;

        for (int i = 0; i < pixels.size(); i++) {
            Pixel oldPixel = pixels.get(i);
            pixels.set(i, new Pixel(oldPixel.x, oldPixel.y + 1));
        }

        return downFigure;
    }

    public boolean isInTheBottom() {
        return findLowestY() == GameScreen.GAME_SCREEN_HEIGHT - 1;
    }

    int findLowestY() {
        int result = 0;
        for (Pixel pixel : pixels) {
            result = Math.max(pixel.y, result);
        }
        return result;
    }

    List<Pixel> getPixelsUnderneath() {
        List<Pixel> pixels = findLowestPixels();
        return pixels.stream()
                .map(p -> new Pixel(p.x, p.y + 1))
                .collect(Collectors.toList());
    }

    List<Pixel> findLowestPixels() {
        Map<Integer, Pixel> tempMap = Maps.newHashMap();
        pixels.stream()
                .forEach(p -> {
                    if (tempMap.get(p.x) == null || tempMap.get(p.x).y < p.y) {
                        tempMap.put(p.x, p);
                    }
                });

        return Lists.newArrayList(tempMap.values());
    }
}
