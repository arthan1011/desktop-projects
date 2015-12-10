package org.arthan.desktop.tetris.model;

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

        assertArrayEquals("Figure wasn't moved down",
                new Pixel[]{
                        new Pixel(4, 1),
                        new Pixel(5, 1),
                        new Pixel(4, 2),
                        new Pixel(5, 2)
                },
                figureOnScreen.getPixels());
    }

    @Test
    public void shouldSayIfInTheBottom() throws Exception {
        FigureOnScreen figureONScreen = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);

        figureONScreen = figureONScreen.goDown();
        assertFalse("Figure should not be in the bottom", figureONScreen.isInTheBottom());

        figureONScreen = figureONScreen.goDown();
        assertTrue("Figure should be in the bottom", figureONScreen.isInTheBottom());
    }

    @Test
    public void shouldFindLowestPixel_Y_Value() throws Exception {
        FigureOnScreen figureOnScreen = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        int expectedLowestY = figureOnScreen.findLowestY();
        assertEquals("Lowest Y was not found", expectedLowestY, 17);
    }

    @Test
    public void shouldCalculatePixelsUnderFigure() throws Exception {
        FigureOnScreen figure = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);

        Pixel[] expectedPixelsUnderFigure = {new Pixel(4, 18), new Pixel(5, 18)};
        Pixel[] actualPixelsUnderFigure = figure.getPixelsUnderneath();

        assertArrayEquals(
                "Pixels under figure wasn't calculated properly",
                expectedPixelsUnderFigure,
                actualPixelsUnderFigure);
    }

    @Test
    public void shouldCalculateLowestPixelsInFigure() throws Exception {
        FigureOnScreen figure = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);

        Pixel[] expectedPixelsUnderFigure = {new Pixel(4, 17), new Pixel(5, 17)};
        Pixel[] actualPixelsUnderFigure = figure.findLowestPixels();

        assertArrayEquals("Lowest pixels in figure wasn't calculated properly", expectedPixelsUnderFigure, actualPixelsUnderFigure);
    }

    private void assertArrayEquals(String message, Object[] expectedArray, Object[] actualArray) {
        assertTrue(
                message +
                        "\nexpected: " + Arrays.toString(expectedArray) +
                        "\nactual: " + Arrays.toString(actualArray)
                ,
                Arrays.equals(expectedArray, actualArray));
    }
}
