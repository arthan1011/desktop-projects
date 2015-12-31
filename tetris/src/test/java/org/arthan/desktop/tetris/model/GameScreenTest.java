package org.arthan.desktop.tetris.model;

import com.google.common.collect.Lists;
import javafx.scene.layout.GridPane;
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

import static org.arthan.desktop.tetris.TestUtils.assertPixelListEquals;
import static org.arthan.desktop.tetris.TestUtils.readBlocksFromFile;


/**
 * Created by ashamsiev on 10.12.2015
 */
public class GameScreenTest {

    private static final String BLOCKS_BEFORE_SEPARATE_ROW_ERASURE = "/blocks_before_separate_row_erasure.txt";
    private static final String BLOCKS_AFTER_SEPARATE_ROW_ERASURE = "/blocks_after_separate_row_erasure.txt";
    private static final String BLOCKS_BEFORE_BLOCKS_IN_BOTTOM_ERASURE = "/blocks_before_blocks_in_bottom_erasure.txt";
    private static final String BLOCKS_AFTER_BLOCKS_IN_BOTTOM_ERASURE = "/blocks_after_blocks_in_bottom_erasure.txt";
    private static final GridPane UNUSED_GAME_GRID = null;
    private static final String BLOCKS_AFTER_ONE_BOTTOM_ROW_ERASURE = "/blocks_after_one_bottom_row_erasure.txt";
    private static final String BLOCKS_BEFORE_ONE_BOTTOM_ROW_ERASURE = "/blocks_before_one_bottom_row_erasure.txt";
    private static final String BLOCKS_BEFORE_THREE_BOTTOM_ROWS_ERASURE = "/blocks_before_three_bottom_rows_erasure.txt";
    private static final String BLOCKS_AFTER_THREE_BOTTOM_ROWS_ERASURE = "/blocks_after_three_bottom_rows_erasure.txt";
    private static final String J_FIGURE_ROTATED_ONE_TIME = "/j_figure_rotated_one_time.txt";
    private static final String J_FIGURE_ROTATED_TWO_TIMES = "/j_figure_rotated_two_times.txt";
    private static final String J_FIGURE_ROTATED_THREE_TIMES = "/j_figure_rotated_three_times.txt";
    private static final String J_FIGURE_ROTATE_FULL_CIRCLE = "/j_figure_rotate_full_circle.txt";
    private static final String S_FIGURE_ROTATED_ONE_TIME = "/s_figure_rotated_one_time.txt";
    private static final String S_FIGURE_ROTATED_FULL_CIRCLE = "/s_figure_rotated_full_circle.txt";
    private static final String Z_FIGURE_ROTATED_ONE_TIME = "/z_figure_rotated_one_time.txt";
    private static final String Z_FIGURE_ROTATED_FULL_CIRCLE = "/z_figure_rotated_full_circle.txt";
    private static final String T_FIGURE_ROTATED_ONE_TIME = "/t_figure_rotated_one_time.txt";
    private static final String T_FIGURE_ROTATED_TWO_TIMES = "/t_figure_rotated_two_times.txt";
    private static final String T_FIGURE_ROTATED_THREE_TIMES = "/t_figure_rotated_three_times.txt";
    private static final String T_FIGURE_ROTATED_FULL_CIRCLE = "/t_figure_rotated_full_circle.txt";
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
    private final FigureProvider UNUSED_PROVIDER = null;

    @Test
    public void shouldPreserveBlocksAfterFigurePositionUpdated() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), null);
        gameScreen.setBlocks(BLOCKS_IN_BOTTOM);
        FigureOnScreen square = Figure.getTestSquareAbove2Bottom();
        gameScreen.setProvider(new FigureListProvider(square));
        gameScreen.nextStep();

        List<Pixel> expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(Figure.getTestSquareAbove2Bottom().getPixels());
        assertPixelListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getGameData());

        gameScreen.nextStep();
        gameScreen.nextStep();

        expectedArray = Lists.newArrayList(BLOCKS_IN_BOTTOM);
        expectedArray.addAll(Figure.getTestSquareAbove1Bottom().getPixels());
        assertPixelListEquals("Blocks state wasn't preserved", expectedArray, gameScreen.getBlocks());
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

        assertPixelListEquals(
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

        assertPixelListEquals(
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

        assertPixelListEquals(
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

        assertPixelListEquals(
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

        assertPixelListEquals(
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
    public void shouldRotateJFigure() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(Figure.getJFigureOnTop())
        );
        gameScreen.nextStep();
        gameScreen.doRotate();

        assertPixelListEquals(
                "J-Figure should rotate one time",
                readBlocksFromFile(J_FIGURE_ROTATED_ONE_TIME),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "J-Figure should rotate two times",
                readBlocksFromFile(J_FIGURE_ROTATED_TWO_TIMES),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "J-Figure should rotate three times",
                readBlocksFromFile(J_FIGURE_ROTATED_THREE_TIMES),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "J-Figure should rotate full circle",
                readBlocksFromFile(J_FIGURE_ROTATE_FULL_CIRCLE),
                gameScreen.getGameData()
        );
    }

    @Test
    public void shouldRotateSShape() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(Figure.getSFigureOnTop())
        );
        gameScreen.nextStep();
        gameScreen.doRotate();

        assertPixelListEquals(
                "S-Figure should rotate",
                readBlocksFromFile(S_FIGURE_ROTATED_ONE_TIME),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "S-Figure should rotate full circle",
                readBlocksFromFile(S_FIGURE_ROTATED_FULL_CIRCLE),
                gameScreen.getGameData()
        );
    }

    @Test
    public void shouldRotateZShape() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(Figure.getZFigureOnTop())
        );
        gameScreen.nextStep();
        gameScreen.doRotate();

        assertPixelListEquals(
                "Z-Figure should rotate one time",
                readBlocksFromFile(Z_FIGURE_ROTATED_ONE_TIME),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "Z-Figure should rotate full circle",
                readBlocksFromFile(Z_FIGURE_ROTATED_FULL_CIRCLE),
                gameScreen.getGameData()
        );
    }

    @Test
    public void shouldRotateTShape() throws Exception {
        GameScreen gameScreen = new GameScreen(
                new GridPane(),
                new FigureListProvider(Figure.getTFigureOnTop())
        );
        gameScreen.nextStep();
        gameScreen.doRotate();

        assertPixelListEquals(
                "T-Figure should rotate one time",
                readBlocksFromFile(T_FIGURE_ROTATED_ONE_TIME),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "T-Figure should rotate two times",
                readBlocksFromFile(T_FIGURE_ROTATED_TWO_TIMES),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "T-Figure should rotate three times",
                readBlocksFromFile(T_FIGURE_ROTATED_THREE_TIMES),
                gameScreen.getGameData()
        );

        gameScreen.doRotate();

        assertPixelListEquals(
                "T-Figure should rotate full circle",
                readBlocksFromFile(T_FIGURE_ROTATED_FULL_CIRCLE),
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

    @Test
    public void shouldEraseSeparatedRows() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), UNUSED_PROVIDER);
        gameScreen.setBlocks(readBlocksFromFile(BLOCKS_BEFORE_SEPARATE_ROW_ERASURE));

        gameScreen.eraseFilledRows();

        assertPixelListEquals(
                "Separated filled rows should properly disappear",
                readBlocksFromFile(BLOCKS_AFTER_SEPARATE_ROW_ERASURE),
                gameScreen.getBlocks()
        );
    }

    @Test
    public void shouldEraseRowsInBottom() throws Exception {
        GameScreen gameScreen = new GameScreen(new GridPane(), UNUSED_PROVIDER);
        gameScreen.setBlocks(readBlocksFromFile(BLOCKS_BEFORE_BLOCKS_IN_BOTTOM_ERASURE));

        gameScreen.eraseFilledRows();

        assertPixelListEquals(
                "Blocks in the bottom should be erased",
                readBlocksFromFile(BLOCKS_AFTER_BLOCKS_IN_BOTTOM_ERASURE),
                gameScreen.getBlocks()
        );
    }

    @Test
    public void shouldEraseOneBottomRow() throws Exception {
        GameScreen gameScreen = new GameScreen(UNUSED_GAME_GRID, UNUSED_PROVIDER);
        gameScreen.setBlocks(readBlocksFromFile(BLOCKS_BEFORE_ONE_BOTTOM_ROW_ERASURE));

        gameScreen.eraseFilledRows();

        assertPixelListEquals(
                "Should erase one row in the bottom",
                readBlocksFromFile(BLOCKS_AFTER_ONE_BOTTOM_ROW_ERASURE),
                gameScreen.getBlocks()
        );
    }

    @Test
    public void shouldEraseThreeBottomRows() throws Exception {
        GameScreen gameScreen = new GameScreen(UNUSED_GAME_GRID, UNUSED_PROVIDER);
        gameScreen.setBlocks(readBlocksFromFile(BLOCKS_BEFORE_THREE_BOTTOM_ROWS_ERASURE));

        gameScreen.eraseFilledRows();

        assertPixelListEquals(
                "Should erase two rows in the bottom",
                readBlocksFromFile(BLOCKS_AFTER_THREE_BOTTOM_ROWS_ERASURE),
                gameScreen.getBlocks()
        );
    }
}