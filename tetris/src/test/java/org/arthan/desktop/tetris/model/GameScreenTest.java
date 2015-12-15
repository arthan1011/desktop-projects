package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.TestUtils;
import org.arthan.desktop.tetris.model.provider.FigureListProvider;
import org.arthan.desktop.tetris.model.provider.FigureProvider;
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
        GameScreen gameScreen = new GameScreen(new GridPane(), null);
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setProvider(new FigureListProvider(square));
        gameScreen.nextStep();

        List<Pixel> expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getGameData());

        gameScreen.nextStep();
        gameScreen.nextStep();

        expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM);
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getBlocks());
    }

    @Test
    public void shouldFindOutIfFigureReachedBlocks() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), null);
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM);
        gameScreen.setProvider(new FigureListProvider(square));

        gameScreen.nextStep();

        Assert.assertFalse("Figure should not reach blocks", gameScreen.figureReachedBlocks());

        gameScreen.nextStep();

        Assert.assertTrue("Figure should reach blocks", gameScreen.figureReachedBlocks());
    }

    @Test
    public void shouldBecomePartOfBlocksAfterFell() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_1_BOTTOM))
        );
        gameScreen.nextStep();
        gameScreen.nextStep();
        gameScreen.nextStep();

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
    public void shouldAutomaticallyLaunchNewFigureOnTop() throws Exception {
        FigureProvider figureProvider = new FigureListProvider(
                new FigureOnScreen(FigureOnScreen.TEST_SQUARE_ABOVE_2_BOTTOM),
                new FigureOnScreen(FigureOnScreen.SQUARE_ON_TOP)
        );
        GameScreen gameScreen = new GameScreen(new GridPane(), figureProvider);

        gameScreen.nextStep();

        TestUtils.assertListEquals(
                "Should show first figure from figure provider",
                Lists.newArrayList(
                        new Pixel(4, 16),
                        new Pixel(5, 16),
                        new Pixel(4, 17),
                        new Pixel(5, 17)
                ),
                gameScreen.getGameData()
        );

        gameScreen.nextStep();
        gameScreen.nextStep();
        gameScreen.nextStep();

        TestUtils.assertListEquals(
                "Second square provided by figure provider should appear on top",
                Lists.newArrayList(
                        new Pixel(4, 0),
                        new Pixel(5, 0),
                        new Pixel(4, 1),
                        new Pixel(5, 1),
                        new Pixel(4, 18),
                        new Pixel(5, 18),
                        new Pixel(4, 19),
                        new Pixel(5, 19)
                ),
                gameScreen.getGameData()
        );
    }
}