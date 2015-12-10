package org.arthan.desktop.tetris;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import static org.arthan.desktop.tetris.model.GameScreen.GAME_SCREEN_HEIGHT;
import static org.arthan.desktop.tetris.model.GameScreen.GAME_SCREEN_WIDTH;

/**
 * Created by Arthur Shamsiev on 28.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TetrisTest extends TestGui {

    private static final String SQUARE_ON_TOP_PATH = "/square_on_top.txt";
    private static final String BLANK_PATH = "/blank.txt";
    private static final String SQUARE_1_PIXEL_BELOW_TOP = "/square_1_pixel_below_top.txt";
    private static final String SQUARE_2_PIXEL_BELOW_TOP = "/square_2_pixel_below_top.txt";
    private static final String SQUARE_2_PIXEL_ABOVE_BOTTOM = "/square_2_pixel_above_bottom.txt";
    private static final String SQUARE_IN_THE_BOTTOM = "/square_in_the_bottom.txt";
    private static final String SQUARE_2_PIXEL_ABOVE_BOTTOM_WITH_BLOCKS_IN_BOTTOM = "/square_2_pixel_above_bottom_with_blocks_in_bottom.txt";
    private static final String SQUARE_STOPPED_AT_BLOCKS_1_PIXEL_ABOVE_BOTTOM = "/square_stopped_at_blocks_1_pixel_above_bottom.txt";

    @Test
    public void shouldStartGameWithBlankScreen() throws Exception {
        click(START_BUTTON_ID);
        GridPane screen = findGameScreen();

        int[][] screenArray = getArrayFromScreen(screen);

        String actualScreenArray = stringifyScreenArray(screenArray);
        String expectedScreenArray = readFile(BLANK_PATH);

        Assert.assertEquals("Game screen on start wasn't blank", expectedScreenArray, actualScreenArray);
    }

    @Test
    public void shouldShowSquareOnGameScreen() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);
        GridPane screen = findGameScreen();

        int[][] screenArray = getArrayFromScreen(screen);

        String actualScreenArray = stringifyScreenArray(screenArray);
        String expectedScreenArray = readFile(SQUARE_ON_TOP_PATH);

        Assert.assertEquals("Square hasn't appeared at the top", expectedScreenArray, actualScreenArray);
    }

    @Test
    public void shouldShowFallingSquare() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        waitFor(1500);
        GridPane screen = findGameScreen();
        int[][] gameArray = getArrayFromScreen(screen);
        String actualScreenArray = stringifyScreenArray(gameArray);
        String expectedScreenArray = readFile(SQUARE_1_PIXEL_BELOW_TOP);
        Assert.assertEquals("Square wasn't falling at expected speed", expectedScreenArray, actualScreenArray);

        waitFor(1000);
        screen = findGameScreen();
        gameArray = getArrayFromScreen(screen);
        actualScreenArray = stringifyScreenArray(gameArray);
        expectedScreenArray = readFile(SQUARE_2_PIXEL_BELOW_TOP);
        Assert.assertEquals("Square wasn't falling at expected speed", expectedScreenArray, actualScreenArray);
    }

    private GridPane findGameScreen() {
        return find(GAME_SCREEN_ID);
    }

    @Test
    public void shouldStopInTheBottom() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        GridPane screen = findGameScreen();
        int[][] gameArray = getArrayFromScreen(screen);
        String actualScreenArray = stringifyScreenArray(gameArray);
        String expectedScreenArray = readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM);
        Assert.assertEquals("Square didn't appear near bottom", expectedScreenArray, actualScreenArray);

        waitFor(3500);
        screen = findGameScreen();
        try {
            gameArray = getArrayFromScreen(screen);
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.fail("Square didn't stop in the bottom");
        }
        actualScreenArray = stringifyScreenArray(gameArray);
        expectedScreenArray = readFile(SQUARE_IN_THE_BOTTOM);
        Assert.assertEquals("Square didn't stop in the bottom", expectedScreenArray, actualScreenArray);
    }

    @Test
    public void shouldStopWhenReachesBlocks() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_IN_BOTTOM);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        GridPane screen = findGameScreen();
        int[][] gameArray = getArrayFromScreen(screen);
        String actualScreenArray = stringifyScreenArray(gameArray);
        String expectedScreenArray = readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM_WITH_BLOCKS_IN_BOTTOM);
        Assert.assertEquals(
                "Initial state with figure near bottom and blocks in the bottom wasn't set",
                expectedScreenArray,
                actualScreenArray);

        waitFor(2500);
        screen = findGameScreen();
        gameArray = getArrayFromScreen(screen);
        actualScreenArray = stringifyScreenArray(gameArray);
        expectedScreenArray = readFile(SQUARE_STOPPED_AT_BLOCKS_1_PIXEL_ABOVE_BOTTOM);
        Assert.assertEquals("Square didn't stop when it reached blocks", expectedScreenArray, actualScreenArray);
    }

    private void click(String buttonID) {
        click(buttonID, MouseButton.PRIMARY);
    }

    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int[][] getArrayFromScreen(GridPane screen) {
        int screenArray[][] = new int[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];

        ObservableList<Node> children = screen.getChildren();
        for (Node aChildren : children) {
            Rectangle cell = (Rectangle) aChildren;
            if (!cell.getFill().equals(Color.LIGHTGREY)) {
                screenArray[GridPane.getRowIndex(cell)][GridPane.getColumnIndex(cell)] = 1;
            }
        }
        return screenArray;
    }

    private String stringifyScreenArray(int[][] screenArray) {
        String result = "";

        for (int[] row : screenArray) {
            for (int cell : row) {
                result += cell == 0 ? "__" : "88";
            }
            result += "\n";
        }
        return result.trim();
    }
}
