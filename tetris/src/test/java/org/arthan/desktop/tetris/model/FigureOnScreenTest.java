package org.arthan.desktop.tetris.model;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class FigureOnScreenTest {

    @Test
    public void shouldChangePositionOnGoDown() throws Exception {
        FigureOnScreen figureOnScreen = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);

        figureOnScreen = figureOnScreen.goDown();

        Assert.assertTrue("Figure wasn't moved down", Arrays.equals(
                new Pixel[] {
                    new Pixel(4, 1),
                    new Pixel(5, 1),
                    new Pixel(4, 2),
                    new Pixel(5, 2)
                },
                figureOnScreen.getPixels()
        ));
    }
}
