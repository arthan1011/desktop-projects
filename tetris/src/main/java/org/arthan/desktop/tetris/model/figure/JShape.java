package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * Created by Arthan on 30.12.2015.
 * Next to Ufa.
 */
public class JShape extends FigureOnScreen {

    private static final Collection<Pixel> J_SHAPE_1 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(0, 2)
    );
    private static final Collection<Pixel> J_SHAPE_2 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(0, 0)
    );
    private static final Collection<Pixel> J_SHAPE_3 = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(2, 0)
    );
    private static final Collection<Pixel> J_SHAPE_4 = Lists.newArrayList(
            new Pixel(0, 1),
            new Pixel(1, 1),
            new Pixel(2, 1),
            new Pixel(2, 2)
    );

    @SuppressWarnings("unchecked")
    private List<List<Pixel>> figureShapes = Lists.newArrayList(
            Lists.newArrayList(J_SHAPE_1),
            Lists.newArrayList(J_SHAPE_2),
            Lists.newArrayList(J_SHAPE_3),
            Lists.newArrayList(J_SHAPE_4)
    );
    private List<int[]> pivotVectors = Lists.newArrayList(
            new int[]{0, 0},
            new int[]{0, 0},
            new int[]{-1, 0},
            new int[]{0, -1}
    );

    @Override
    public FigureOnScreen onTop() {
        return Figure.getJFigureOnTop();
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
        JShape jShape = new JShape();
        jShape.setShapeIndex(shapeIndex);
        return jShape;
    }

    public static FigureOnScreen build(List<Pixel> figurePosition) {
        JShape jShape = new JShape();
        jShape.setCurrentPositionPixels(figurePosition);
        return jShape;
    }
}
