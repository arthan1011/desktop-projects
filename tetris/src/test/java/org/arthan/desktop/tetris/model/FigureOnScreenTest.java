package org.arthan.desktop.tetris.model;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class FigureOnScreenTest {

    @Test
    public void shouldChangePositionOnGoDown() throws Exception {
        FigureOnScreen figureOnScreen = new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP);

        figureOnScreen = figureOnScreen.goDown();

        assertTrue("Figure wasn't moved down", Arrays.equals(
                new Pixel[]{
                        new Pixel(4, 1),
                        new Pixel(5, 1),
                        new Pixel(4, 2),
                        new Pixel(5, 2)
                },
                figureOnScreen.getPixels()
        ));
    }

    @Test
    public void shouldSayIfInTheBottom() throws Exception {
        FigureOnScreen figureONScreen = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_NEAR_BOTTOM);

        figureONScreen = figureONScreen.goDown();
        assertFalse("Figure should not be in the bottom", figureONScreen.isInTheBottom());

        figureONScreen = figureONScreen.goDown();
        assertTrue("Figure should be in the bottom", figureONScreen.isInTheBottom());
    }

    @Test
    public void shouldFindLowestPixel_Y_Value() throws Exception {
        FigureOnScreen figureOnScreen = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_NEAR_BOTTOM);
        int expectedLowestY = figureOnScreen.findLowestY();
        assertEquals("Lowest Y was not found", expectedLowestY, 17);
    }
}
