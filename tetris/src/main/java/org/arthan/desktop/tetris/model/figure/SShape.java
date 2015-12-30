package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by Arthan on 31.12.2015.
 * Next to Ufa.
 */
public class SShape extends FigureOnScreen {

    private static final Collection<Pixel> S_SHAPE_1 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(2, 0)
    );
    private static final Collection<Pixel> S_SHAPE_2 = Lists.newArrayList(
            new Pixel(0, 0),
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(1, 2)
    );

    @SuppressWarnings("unchecked")
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(S_SHAPE_1),
            Lists.newArrayList(S_SHAPE_2)
    );

    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{0, 0},
            new int[]{0, 0}
    );

    @Override
    public FigureOnScreen onTop() {
        return Figure.getSFigureOnTop();
    }

    @Override
    public List<List<Pixel>> getFigureShapes() {
        return figureShapes;
    }

    @Override
    public List<int[]> getPivotVectors() {
        return pivotVectors;
    }

    @Override
    protected FigureOnScreen createEmpty(int shapeIndex) {
        SShape sShape = new SShape();
        sShape.setShapeIndex(shapeIndex);
        return sShape;
    }

    public static FigureOnScreen build(List<Pixel> figurePosition) {
        SShape sShape = new SShape();
        sShape.setCurrentPositionPixels(figurePosition);
        return sShape;
    }
}
