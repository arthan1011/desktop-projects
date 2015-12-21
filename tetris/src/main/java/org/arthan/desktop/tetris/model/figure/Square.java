package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Артур on 20.12.2015.
 * Next to Ufa.
 */
public class Square extends FigureOnScreen {

    private static final Collection<Pixel> SQUARE_SHAPE = Lists.newArrayList(
            new Pixel(0, 0),
            new Pixel(0, 1),
            new Pixel(1, 0),
            new Pixel(1, 1)
    );
    private static List<Pixel> figureShape = Lists.newArrayList(SQUARE_SHAPE);

    public static FigureOnScreen build(ArrayList<Pixel> pixels) {
        Square square = new Square();
        square.setCurrentPositionPixels(pixels);
        return square;
    }

    @Override
    protected FigureOnScreen createEmpty() {
        return new Square();
    }

    @Override
    public FigureOnScreen rotate() {
        return this;
    }
}
