package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ����� on 24.12.2015.
 * Next to Ufa.
 */
public class LShape extends FigureOnScreen {

    private static final Collection<Pixel> L_SHAPE_1 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(2, 2)
    );
    private static final Collection<Pixel> L_SHAPE_2 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(0, 2)
    );
    private static final Collection<Pixel> L_SHAPE_3 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(0, 0)
    );
    private static final Collection<Pixel> L_SHAPE_4 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(2, 0)
    );

    @SuppressWarnings("unchecked")
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(L_SHAPE_1),
            Lists.newArrayList(L_SHAPE_2),
            Lists.newArrayList(L_SHAPE_3),
            Lists.newArrayList(L_SHAPE_4)
    );
    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{-1, 0},
            new int[]{0, -1},
            new int[]{0, 0},
            new int[]{0, 0}
    );

    @Override
    protected FigureOnScreen createEmpty(int shapeIndex) {
        LShape shape = new LShape();
        shape.setShapeIndex(shapeIndex);
        return shape;
    }

    public static FigureOnScreen build(ArrayList<Pixel> pixels) {
        LShape figure = new LShape();
        figure.setCurrentPositionPixels(pixels);
        return figure;
    }

    @Override
    public FigureOnScreen onTop() {
        return Figure.getLFigureOnTop();
    }

    @Override
    public List<int[]> getPivotVectors() {
        return pivotVectors;
    }

    @Override
    public List<List<Pixel>> getFigureShapes() {
        return figureShapes;
    }
}
