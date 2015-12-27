package org.arthan.desktop.tetris.model.figure;

import com.google.common.collect.Lists;
import org.arthan.desktop.tetris.TestUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class FigureOnScreenTest {

    @Test
    public void shouldChangePositionOnGoDown() throws Exception {
        FigureOnScreen figureOnScreen = Figure.getSquareOnTop();

        figureOnScreen = figureOnScreen.goDown();

        TestUtils.assertListEquals("Figure wasn't moved down",
                Lists.newArrayList(
                        new Pixel(4, 1),
                        new Pixel(5, 1),
                        new Pixel(4, 2),
                        new Pixel(5, 2)
                ),
                figureOnScreen.getPixels());
    }

    @Test
    public void shouldSayIfInTheBottom() throws Exception {
        FigureOnScreen figureOnScreen = Figure.getTestSquareAbove2Bottom();

        figureOnScreen = figureOnScreen.goDown();
        assertFalse("Figure should not be in the bottom", figureOnScreen.isInTheBottom());

        figureOnScreen = figureOnScreen.goDown();
        assertTrue("Figure should be in the bottom", figureOnScreen.isInTheBottom());
    }

    @Test
    public void shouldCalculatePixelsUnderFigure() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();

        List<Pixel> expectedPixelsUnderFigure = Lists.newArrayList(new Pixel(4, 18), new Pixel(5, 18));
        List<Pixel> actualPixelsUnderFigure = figure.getPixelsUnderneath();

        TestUtils.assertListEquals(
                "Pixels under figure wasn't calculated properly",
                expectedPixelsUnderFigure,
                actualPixelsUnderFigure);
    }

    @Test
    public void shouldCalculateLowestPixelsInFigure() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();

        List<Pixel> expectedPixelsUnderFigure = Lists.newArrayList(new Pixel(4, 17), new Pixel(5, 17));
        List<Pixel> actualPixelsUnderFigure = figure.findLowestPixels();

        TestUtils.assertListEquals("Lowest pixels in figure wasn't calculated properly", expectedPixelsUnderFigure, actualPixelsUnderFigure);
    }

    @Test
    public void shouldGoRight() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();
        figure = figure.goRight();

        List<Pixel> expectedPixelsAfterGoRight = Lists.newArrayList(
                new Pixel(5, 16),
                new Pixel(6, 16),
                new Pixel(5, 17),
                new Pixel(6, 17)
        );

        TestUtils.assertListEquals(
                "Figure didn't move to the right",
                expectedPixelsAfterGoRight,
                figure.getPixels()
        );
    }

    @Test
    public void shouldNotGoRightBeyondScreen() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();

        figure = figure.goRight();
        figure = figure.goRight();
        figure = figure.goRight();
        figure = figure.goRight();

        List<Pixel> expectedPixelsByRightBorder = Lists.newArrayList(
                new Pixel(8, 16),
                new Pixel(9, 16),
                new Pixel(8, 17),
                new Pixel(9, 17)
        );

        TestUtils.assertListEquals(
                "Figure should be by right border",
                expectedPixelsByRightBorder,
                figure.getPixels()
        );

        figure = figure.goRight();

        TestUtils.assertListEquals(
                "Figure should still be by right border",
                expectedPixelsByRightBorder,
                figure.getPixels()
        );
    }

    @Test
    public void shouldGoLeft() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();
        figure = figure.goLeft();

        List<Pixel> expectedPixelsAfterGoLeft = Lists.newArrayList(
                new Pixel(3, 16),
                new Pixel(4, 16),
                new Pixel(3, 17),
                new Pixel(4, 17)
        );

        TestUtils.assertListEquals(
                "Figure should go left",
                expectedPixelsAfterGoLeft,
                figure.getPixels()
        );
    }

    @Test
    public void shouldNotGoLeftBeyondScreen() throws Exception {
        FigureOnScreen figure = Figure.getTestSquareAbove2Bottom();

        figure = figure.goLeft();
        figure = figure.goLeft();
        figure = figure.goLeft();
        figure = figure.goLeft();

        List<Pixel> expectedPixelsByLeftBorder = Lists.newArrayList(
                new Pixel(0, 16),
                new Pixel(1, 16),
                new Pixel(0, 17),
                new Pixel(1, 17)
        );

        TestUtils.assertListEquals(
                "Figure should be by left border",
                expectedPixelsByLeftBorder,
                figure.getPixels()
        );

        figure = figure.goLeft();

        TestUtils.assertListEquals(
                "Figure should still be by left border",
                expectedPixelsByLeftBorder,
                figure.getPixels()
        );
    }

    @Test
    public void shouldRotateStickFigure() throws Exception {
        FigureOnScreen figure = Figure.getStickOnTop();
        figure = figure.rotate();

        TestUtils.assertListEquals(
                "Stick should rotate",
                Lists.newArrayList(
                        new Pixel(3, 1),
                        new Pixel(4, 1),
                        new Pixel(5, 1),
                        new Pixel(6, 1)
                ),
                figure.getPixels()
        );
    }
}
