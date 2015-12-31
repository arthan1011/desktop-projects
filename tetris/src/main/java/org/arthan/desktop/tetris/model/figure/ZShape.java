package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by Arthan on 31.12.2015.
 * Next to Ufa.
 */
public class ZShape extends FigureOnScreen {

    private static final Collection<Pixel> Z_SHAPE_1 = Lists.newArrayList(
            new Pixel(0, 0),
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(2, 1)
    );
    private static final Collection<Pixel> Z_SHAPE_2 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(0, 2)
    );

    @SuppressWarnings("unchecked")
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(Z_SHAPE_1),
            Lists.newArrayList(Z_SHAPE_2)
    );

    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{0, 0},
            new int[]{0, 0}
    );

    @Override
    public FigureOnScreen onTop() {
        return Figure.getZFigureOnTop();
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
        ZShape zShape = new ZShape();
        zShape.setShapeIndex(shapeIndex);
        return zShape;
    }

    public static FigureOnScreen build(List<Pixel> zFigureOnTop) {
        ZShape zShape = new ZShape();
        zShape.setCurrentPositionPixels(zFigureOnTop);
        return zShape;
    }
}
