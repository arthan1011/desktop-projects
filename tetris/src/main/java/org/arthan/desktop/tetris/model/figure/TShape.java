package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by Arthan on 31.12.2015.
 * Next to Ufa.
 */
public class TShape extends FigureOnScreen {

    private static Collection<Pixel> T_SHAPE_1 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1)
    );
    private static Collection<Pixel> T_SHAPE_2 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(2, 1)
    );
    private static Collection<Pixel> T_SHAPE_3 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(1, 2)
    );
    private static Collection<Pixel> T_SHAPE_4 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(0, 1)
    );

    @SuppressWarnings("unchecked")
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(T_SHAPE_1),
            Lists.newArrayList(T_SHAPE_2),
            Lists.newArrayList(T_SHAPE_3),
            Lists.newArrayList(T_SHAPE_4)
    );

    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{0, 0},
            new int[]{-1, 0},
            new int[]{0, -1},
            new int[]{0, 0}
    );

    @Override
    public FigureOnScreen onTop() {
        return Figure.getTFigureOnTop();
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
        TShape tShape = new TShape();
        tShape.setShapeIndex(shapeIndex);
        return tShape;
    }

    public static FigureOnScreen build(List<Pixel> tFigureOnTop) {
        TShape tShape = new TShape();
        tShape.setCurrentPositionPixels(tFigureOnTop);
        return tShape;
    }
}
