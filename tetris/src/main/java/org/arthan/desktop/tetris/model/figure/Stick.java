package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Артур on 20.12.2015.
 * Next to Ufa.
 */
public class Stick extends FigureOnScreen {

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
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(STICK_SHAPE_1),
            Lists.newArrayList(STICK_SHAPE_2)
    );
    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{-2, 0},
            new int[]{0, -1}
    );

    @Override
    public List<List<Pixel>> getFigureShapes() {
        return figureShapes;
    }

    @Override
    public List<int[]> getPivotVectors() {
        return pivotVectors;
    }

    public static FigureOnScreen build(ArrayList<Pixel> pixels) {
        Stick stick = new Stick();
        stick.setCurrentPositionPixels(pixels);
        return stick;
    }

    @Override
    public FigureOnScreen onTop() {
        return Figure.getStickOnTop();
    }

    @Override
    protected FigureOnScreen createEmpty(int shapeIndex) {
        Stick stick = new Stick();
        stick.setShapeIndex(shapeIndex);
        return stick;
    }


}
