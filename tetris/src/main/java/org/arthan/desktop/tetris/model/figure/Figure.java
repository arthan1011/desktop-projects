package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by Artur on 21.12.2015.
 * Next to Ufa.
 */
public class Figure {
    private static final ArrayList<Pixel> SQUARE_ON_TOP_ARRAY = Lists.newArrayList(
            new Pixel(4, 0),
            new Pixel(5, 0),
            new Pixel(4, 1),
            new Pixel(5, 1)
    );
    private static final ArrayList<Pixel> TEST_SQUARE_ABOVE_1_BOTTOM_ARRAY = Lists.newArrayList(
            new Pixel(4, 17),
            new Pixel(5, 17),
            new Pixel(4, 18),
            new Pixel(5, 18)
    );
    private static final ArrayList<Pixel> TEST_SQUARE_ABOVE_2_BOTTOM_ARRAY = Lists.newArrayList(
            new Pixel(4, 16),
            new Pixel(5, 16),
            new Pixel(4, 17),
            new Pixel(5, 17)
    );
    private static final ArrayList<Pixel> TEST_SQUARE_ABOVE_3_BOTTOM_ARRAY = Lists.newArrayList(
            new Pixel(4, 15),
            new Pixel(5, 15),
            new Pixel(4, 16),
            new Pixel(5, 16)
    );
    private static final ArrayList<Pixel> STICK_ON_TOP_ARRAY = Lists.newArrayList(
            new Pixel(5, 0),
            new Pixel(5, 1),
            new Pixel(5, 2),
            new Pixel(5, 3)
    );

    private static final ArrayList<Pixel> L_FIGURE_ON_TOP_ARRAY = Lists.newArrayList(
            new Pixel(4, 0),
            new Pixel(4, 1),
            new Pixel(4, 2),
            new Pixel(5, 2)
    );
    private static final ArrayList<Pixel> STICK_NEAR_LEFT_ON_TOP_ARRAY = Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(1, 3)
    );
    private static final ArrayList<Pixel> STICK_NEAR_RIGHT_ON_TOP_ARRAY = Lists.newArrayList(
            new Pixel(8, 2),
            new Pixel(8, 3),
            new Pixel(8, 4),
            new Pixel(8, 5)
    );
    private static final ArrayList<Pixel> STICK_ABOVE_BOTTOM_ARRAY = Lists.newArrayList(
            new Pixel(4, 16),
            new Pixel(4, 17),
            new Pixel(4, 18),
            new Pixel(4, 19)
    );

    public static FigureOnScreen getStickOnTop() {
        return Stick.build(STICK_ON_TOP_ARRAY);
    }

    public static FigureOnScreen getLFigureOnTop() {
        return LShape.build(L_FIGURE_ON_TOP_ARRAY);
    }

    public static FigureOnScreen getStickNearLeftOnTop() {
        return Stick.build(STICK_NEAR_LEFT_ON_TOP_ARRAY);
    }

    public static FigureOnScreen getStickNearRightOnTop() {
        return Stick.build(STICK_NEAR_RIGHT_ON_TOP_ARRAY);
    }

    public static FigureOnScreen getStickAboveBottom() {
        return Stick.build(STICK_ABOVE_BOTTOM_ARRAY);
    }

    public static FigureOnScreen getTestSquareAbove3Bottom() {
        return Square.build(TEST_SQUARE_ABOVE_3_BOTTOM_ARRAY);
    }

    public static FigureOnScreen getTestSquareAbove2Bottom() {
        return Square.build(TEST_SQUARE_ABOVE_2_BOTTOM_ARRAY);
    }

    public static FigureOnScreen getTestSquareAbove1Bottom() {
        return Square.build(TEST_SQUARE_ABOVE_1_BOTTOM_ARRAY);
    }

    public static FigureOnScreen getSquareOnTop() {
        return Square.build(SQUARE_ON_TOP_ARRAY);
    }
}
