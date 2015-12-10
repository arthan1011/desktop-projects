package org.arthan.desktop.tetris.model;

import javafx.scene.layout.GridPane;
import junit.framework.Assert;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by ashamsiev on 10.12.2015
 */
public class GameScreenTest {

    private final Pixel[] BLOCKS_IN_BOTTOM = new Pixel[]{
            new Pixel(0, 19),
            new Pixel(1, 19),
            new Pixel(2, 19),
            new Pixel(3, 19),
            new Pixel(4, 19),
            new Pixel(5, 19),
            new Pixel(6, 19),
            new Pixel(7, 19),
            new Pixel(8, 19),
            new Pixel(9, 19)
    };

    @Test
    public void shouldPreserveBlocksAfterFigurePositionUpdated() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setFigure(square);

        Pixel[] expectedArray = (Pixel[]) ArrayUtils.addAll(BLOCKS_IN_BOTTOM, FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        Assert.assertTrue("Blocks state wasn't preserved", Arrays.equals(expectedArray, gameScreen.getPixelArray()));

        gameScreen.figureDown();

        expectedArray = (Pixel[]) ArrayUtils.addAll(BLOCKS_IN_BOTTOM, FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM);
        Assert.assertTrue("Blocks state wasn't preserved", Arrays.equals(expectedArray, gameScreen.getPixelArray()));
    }

    @Test
    public void shouldFindOutIfFigureReachedBlocks() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setFigure(square);

        Assert.assertFalse("Figure did not reach blocks", gameScreen.figureReachedBlocks());

        gameScreen.figureDown();

        Assert.assertTrue("Figure did not reach blocks", gameScreen.figureReachedBlocks());
    }
}