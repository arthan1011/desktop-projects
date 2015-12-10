package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * Created by ashamsiev on 10.12.2015
 */
public class GameScreenTest {

    private final List<Pixel> BLOCKS_IN_BOTTOM = Lists.newArrayList(
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
    );

    @Test
    public void shouldPreserveBlocksAfterFigurePositionUpdated() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setFigure(square);

        List<Pixel> expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getPixelArray());

        gameScreen.figureDown();

        expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM);
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getBlocks());
    }

    @Test
    public void shouldFindOutIfFigureReachedBlocks() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setFigure(square);

        Assert.assertFalse("Figure did not reach blocks", gameScreen.ifFigureReachedBlocks());

        GAME_STATUS status = gameScreen.figureDown();

        Assert.assertEquals("Figure did not reach blocks", GAME_STATUS.STOPPED, status);
    }

    @Test
    public void shouldBecomePartOfBlocksAfterFell() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setFigure(new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM));
        gameScreen.figureDown();

        TestUtils.assertListEquals(
                "Figure didn't become part of blocks after reaching bottom",
                Lists.newArrayList(
                    new Pixel(4, 18),
                    new Pixel(5, 18),
                    new Pixel(4, 19),
                    new Pixel(5, 19)
                ),
                gameScreen.getBlocks()
        );
    }

    @Test
    public void shouldReturnUpdateStatus() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setFigure(new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM));

        GAME_STATUS status = gameScreen.figureDown();
        Assert.assertEquals(
                "Game status should be FALLING",
                GAME_STATUS.FALLING,
                status);

        status = gameScreen.figureDown();

        Assert.assertEquals(
                "Game status should be STOPPED",
                GAME_STATUS.STOPPED,
                status);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfFigureDownAfterFell() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane());
        gameScreen.setFigure(new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM));

        gameScreen.figureDown();
        gameScreen.figureDown();
    }
}