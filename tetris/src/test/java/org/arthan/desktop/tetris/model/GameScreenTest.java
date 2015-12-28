package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.scene.layout.GridPane;
import org.arthan.desktop.tetris.TestUtils;
import org.arthan.desktop.tetris.model.figure.Figure;
import org.arthan.desktop.tetris.model.figure.FigureOnScreen;
import org.arthan.desktop.tetris.model.figure.Pixel;
import org.arthan.desktop.tetris.model.figure.Stick;
import org.arthan.desktop.tetris.model.provider.FigureListProvider;
import org.arthan.desktop.tetris.model.provider.FigureProvider;
import org.arthan.desktop.tetris.model.provider.InfiniteFigureProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ashamsiev on 10.12.2015
 */
public class GameScreenTest {

    private static final FigureProvider UNUSED_PROVIDER = null;
    private final List<Pixel> BLOCKS_IN_BOTTOM = Lists.newArrayList(
            new Pixel(0, 19),
            new Pixel(1, 19),
            new Pixel(2, 19),
            new Pixel(3, 19),
            new Pixel(4, 19),
            new Pixel(5, 19),
            new Pixel(6, 19),
            new Pixel(8, 19),
            new Pixel(9, 19)
    );

    @Test
    public void shouldPreserveBlocksAfterFigurePositionUpdated() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), null);
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = Figure.getTestSquareAbove2Bottom();
        gameScreen.setProvider(new FigureListProvider(square));
        gameScreen.nextStep();

        List<Pixel> expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(Figure.getTestSquareAbove2Bottom().getPixels());
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getGameData());

        gameScreen.nextStep();
        gameScreen.nextStep();

        expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(Figure.getTestSquareAbove1Bottom().getPixels());
        TestUtils.assertListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getBlocks());
    }

    @Test
    public void shouldFindOutIfFigureReachedBlocks() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), null);
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = Figure.getTestSquareAbove2Bottom();
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
                new FigureListProvider(Figure.getTestSquareAbove1Bottom())
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
                Figure.getTestSquareAbove2Bottom(),
                Figure.getSquareOnTop()
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

    @Test
    public void shouldGoToBottom() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new InfiniteFigureProvider(Figure.getSquareOnTop())
        );

        gameScreen.nextStep();
        gameScreen.goBottom();

        TestUtils.assertListEquals(
                "Square should be in the bottom",
                Lists.newArrayList(
                        new Pixel(4, 18),
                        new Pixel(5, 18),
                        new Pixel(4, 19),
                        new Pixel(5, 19)
                ),
                gameScreen.getGameData()
        );

    }

    @Test
    public void shouldRotateStick() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(Figure.getStickOnTop())
        );
        gameScreen.nextStep();
        gameScreen.nextStep();
        gameScreen.doRotate();

        TestUtils.assertListEquals(
                "Stick should rotate",
                Lists.newArrayList(
                        new Pixel(3, 2),
                        new Pixel(4, 2),
                        new Pixel(5, 2),
                        new Pixel(6, 2)
                ),
                gameScreen.getGameData()
        );
    }

    @Test
    public void figureShouldCollide() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), UNUSED_PROVIDER);
        ArrayList<Pixel> blocksOnTop = Lists.newArrayList(
                new Pixel(4, 0),
                new Pixel(4, 1),
                new Pixel(4, 2),
                new Pixel(4, 3)
        );
        gameScreen.setBlocks(blocksOnTop);
        FigureOnScreen stickOnTop = Stick.build(Lists.newArrayList(
                new Pixel(2, 1),
                new Pixel(3, 1),
                new Pixel(4, 1),
                new Pixel(5, 1)
        ));

        Assert.assertTrue("Figure should collide", gameScreen.figureCollides(stickOnTop));

    }
}