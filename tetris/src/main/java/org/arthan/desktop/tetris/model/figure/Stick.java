package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;
import org.arthan.desktop.tetris.Main;
import org.arthan.desktop.tetris.model.GameScreen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Артур on 20.12.2015.
 * Next to Ufa.
 */
public class Stick extends FigureOnScreen {

    private int shapeIndex = 0;

    private static final Collection<Pixel> STICK_SHAPE_1 = Lists.newArrayList(
            new Pixel(2, 0),
            new Pixel(2, 1),
            new Pixel(2, 2),
            new Pixel(2, 3)
    );
    private static final Collection<Pixel> STICK_SHAPE_2 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(3, 1)
    );
    @SuppressWarnings("unchecked")
    private static List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(STICK_SHAPE_1),
            Lists.newArrayList(STICK_SHAPE_2)
    );
    private static List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{-2, 0},
            new int[]{0, -1}
    );

    public static FigureOnScreen build(ArrayList<Pixel> pixels) {
        Stick stick = new Stick();
        stick.setCurrentPositionPixels(pixels);
        return stick;
    }

    @Override
    protected FigureOnScreen createEmpty() {
        return new Stick();
    }

    @Override
    public FigureOnScreen rotate() {

        Pixel topLeftestPixel = findTopLeftestPixel();

        List<Pixel> rotatePixels = figureShapes.get(++shapeIndex % figureShapes.size())
                .stream()
                .map(p -> new Pixel(p.x + topLeftestPixel.x, p.y + topLeftestPixel.y))
                .collect(Collectors.toList());
        Stick rotateStick = new Stick();
        rotateStick.setCurrentPositionPixels(rotatePixels);
        return rotateStick;
    }

    private Pixel findTopLeftestPixel() {
        int minX = GameScreen.GAME_SCREEN_WIDTH;
        int minY = GameScreen.GAME_SCREEN_HEIGHT;

        for (Pixel pixel : getPixels()) {
            minX = Math.min(pixel.x, minX);
            minY = Math.min(pixel.y, minY);
        }

        int[] vector = pivotVectors.get(shapeIndex);

        return new Pixel(minX + vector[0], minY + vector[1]);
    }


}
