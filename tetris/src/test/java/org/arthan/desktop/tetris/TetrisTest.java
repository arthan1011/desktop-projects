package org.arthan.desktop.tetris;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.controller.GameScreenController;
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
    private static final String SQUARE_IN_THE_BOTTOM_AND_SECOND_SQUARE_ABOVE_IT = "/square_in_the_bottom_and_second_square_above_it.txt";
    private static final String SQUARE_ON_TOP_OF_ANOTHER_SQUARE_IN_THE_BOTTOM = "/square_on_top_of_another_square_in_the_bottom.txt";
    private static final String SQUARE_ON_TOP_AND_FALLEN_SQUARE = "/square_on_top_and_fallen_square.txt";
    private static final int INTERVAL_LT_FASTEST_STEP = GameScreenController.FASTEST_STEP_MILLIS / 2;
    private static final String SQUARE_5_PIXEL_BELOW_TOP = "/square_5_pixel_below_top.txt";
    private static final String SQUARE_7_PIXEL_BELOW_TOP = "/square_7_pixel_below_top.txt";

    @Test
    public void shouldStartGameWithBlankScreen() throws Exception {
        click(START_BUTTON_ID);

        Assert.assertEquals(
                "Game screen on start wasn't blank",
                readFile(BLANK_PATH),
                getGameData());
    }

    @Test
    public void shouldShowSquareOnGameScreen() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        Assert.assertEquals(
                "Square hasn't appeared at the top",
                readFile(SQUARE_ON_TOP_PATH),
                getGameData());
    }

    @Test
    public void shouldShowFallingSquare() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        waitFor(stepsBeyond(1));
        Assert.assertEquals(
                "Square wasn't falling at expected speed",
                readFile(SQUARE_1_PIXEL_BELOW_TOP),
                getGameData());

        waitFor(steps(1));
        Assert.assertEquals(
                "Square wasn't falling at expected speed",
                readFile(SQUARE_2_PIXEL_BELOW_TOP),
                getGameData());
    }

    @Test
    public void shouldStopInTheBottom() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        Assert.assertEquals(
                "Square didn't appear near bottom",
                readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM),
                getGameData());

        waitFor(stepsBeyond(3));

        String gameArray = null;
        try {
            gameArray = getGameData();
        } catch (ArrayIndexOutOfBoundsException e) {
            Assert.fail("Square didn't stop in the bottom");
        }
        Assert.assertEquals(
                "Square didn't stop in the bottom",
                readFile(SQUARE_IN_THE_BOTTOM),
                gameArray);
    }

    @Test
    public void shouldStopWhenReachesBlocks() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_IN_BOTTOM);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        Assert.assertEquals(
                "Initial state with figure near bottom and blocks in the bottom wasn't set",
                readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM_WITH_BLOCKS_IN_BOTTOM),
                getGameData());

        waitFor(stepsBeyond(2));
        Assert.assertEquals(
                "Square didn't stop when it reached blocks",
                readFile(SQUARE_STOPPED_AT_BLOCKS_1_PIXEL_ABOVE_BOTTOM),
                getGameData());
    }

    @Test
    public void shouldBecomeBlocksWhenFell() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_2_SQUARES_3_PIXEL_ABOVE_BOTTOM);

        waitFor(stepsBeyond(4));
        Assert.assertEquals(
                "Second square wasn't launched after first fell",
                readFile(SQUARE_IN_THE_BOTTOM_AND_SECOND_SQUARE_ABOVE_IT),
                getGameData());

        waitFor(steps(2));
        Assert.assertEquals(
                "Second square didn't fall on top of first one",
                readFile(SQUARE_ON_TOP_OF_ANOTHER_SQUARE_IN_THE_BOTTOM),
                getGameData()
        );
    }

    @Test
    public void shouldAutomaticallyLaunchNextFigureOnTop() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE2_PIXEL_ABOVE_BOTTOM_WITH_FIGURE_PROVIDER);

        Assert.assertEquals(
                "Square should appear near bottom",
                readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM),
                getGameData());

        waitFor(stepsBeyond(3));

        Assert.assertEquals(
                "Square should appear on top after first one fell",
                readFile(SQUARE_ON_TOP_AND_FALLEN_SQUARE),
                getGameData()
        );

    }

    @Test
    public void shouldChangeSpeed() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);
        waitFor(steps(2) + INTERVAL_LT_FASTEST_STEP);

        Assert.assertEquals(
                "Should appear 2 pixels below top",
                readFile(SQUARE_2_PIXEL_BELOW_TOP),
                getGameData()
        );

        click(TEST_SET_SPEED_5);
        waitFor(steps(3));

        Assert.assertEquals(
                "Should appear 5 pixels below top",
                readFile(SQUARE_5_PIXEL_BELOW_TOP),
                getGameData()
        );

        click(TEST_SET_SPEED_2);
        waitFor(steps(2));
        Assert.assertEquals(
                "Should appear 7 pixels below top",
                readFile(SQUARE_7_PIXEL_BELOW_TOP),
                getGameData()
        );
    }

    private int stepsBeyond(int number) {
        return steps(number) + INTERVAL_LT_FASTEST_STEP;
    }

    private int steps(int number) {
        return 1000 / getCurrentSpeed() * number;
    }

    private GridPane findGameScreen() {
        return find(GAME_SCREEN_ID);
    }

    private String getGameData() {
        GridPane screen = findGameScreen();
        int[][] gameArray = getArrayFromScreen(screen);
        return stringifyScreenArray(gameArray);
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

    public int getCurrentSpeed() {
        Label speedLabel =  find(CURRENT_SPEED_LABEL);
        return Integer.parseInt(speedLabel.getText());
    }
}
