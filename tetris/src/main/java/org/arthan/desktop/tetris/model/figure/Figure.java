package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

/**
 * Created by Артур on 21.12.2015.
 * Next to Ufa.
 */
public class Figure {
    public static final FigureOnScreen SQUARE_ON_TOP = Square.build(Lists.newArrayList(
            new Pixel(4, 0),
            new Pixel(5, 0),
            new Pixel(4, 1),
            new Pixel(5, 1)
    ));
    public static final FigureOnScreen TEST_SQUARE_ABOVE_1_BOTTOM = Square.build(Lists.newArrayList(
            new Pixel(4, 17),
            new Pixel(5, 17),
            new Pixel(4, 18),
            new Pixel(5, 18)
    ));
    public static final FigureOnScreen TEST_SQUARE_ABOVE_2_BOTTOM = Square.build(Lists.newArrayList(
            new Pixel(4, 16),
            new Pixel(5, 16),
            new Pixel(4, 17),
            new Pixel(5, 17)
    ));
    public static final FigureOnScreen TEST_SQUARE_ABOVE_3_BOTTOM = Square.build(Lists.newArrayList(
            new Pixel(4, 15),
            new Pixel(5, 15),
            new Pixel(4, 16),
            new Pixel(5, 16)
    ));
    public static final FigureOnScreen STICK_ON_TOP = Stick.build(Lists.newArrayList(
            new Pixel(5, 0),
            new Pixel(5, 1),
            new Pixel(5, 2),
            new Pixel(5, 3)
    ));

    public static final FigureOnScreen L_FIGURE_ON_TOP = LShape.build(Lists.newArrayList(
            new Pixel(4, 0),
            new Pixel(4, 1),
            new Pixel(4, 2),
            new Pixel(5, 2)
    ));
    public static final FigureOnScreen STICK_NEAR_LEFT_ON_TOP = Stick.build(Lists.newArrayList(
            new Pixel(1, 0),
            new Pixel(1, 1),
            new Pixel(1, 2),
            new Pixel(1, 3)
    ));
    public static final FigureOnScreen STICK_NEAR_RIGHT_ON_TOP = Stick.build(Lists.newArrayList(
            new Pixel(8, 2),
            new Pixel(8, 3),
            new Pixel(8, 4),
            new Pixel(8, 5)
    ));
    public static final FigureOnScreen STICK_ABOVE_BOTTOM = Stick.build(Lists.newArrayList(
            new Pixel(4, 16),
            new Pixel(4, 17),
            new Pixel(4, 18),
            new Pixel(4, 19)
    ));
}
