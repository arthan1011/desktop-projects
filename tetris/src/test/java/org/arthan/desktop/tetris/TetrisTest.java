package org.arthan.desktop.tetris;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.controller.GameScreenController;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.util.UIBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.arthan.desktop.tetris.TestUtils.readFile;
import static org.arthan.desktop.tetris.model.GameScreen.GAME_SCREEN_HEIGHT;
import static org.arthan.desktop.tetris.model.GameScreen.GAME_SCREEN_WIDTH;
import static org.junit.Assert.assertEquals;

/**
 * Created by Arthur Shamsiev on 28.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TetrisTest extends TestGui {

    private static final int INTERVAL_LT_FASTEST_STEP = GameScreenController.FASTEST_STEP_MILLIS / 2;
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
    private static final String SQUARE_5_PIXEL_BELOW_TOP = "/square_5_pixel_below_top.txt";
    private static final String SQUARE_7_PIXEL_BELOW_TOP = "/square_7_pixel_below_top.txt";
    private static final String SQUARE_3_PIXEL_ABOVE_BOTTOM_1_PIXEL_RIGHT = "/square_3_pixel_above_bottom_1_pixel_right.txt";
    private static final String SQUARE_IN_THE_BOTTOM_AND_1_PIXEL_RIGHT = "/square_in_the_bottom_and_1_pixel_right.txt";
    private static final String SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_3_PIXEL_ABOVE_BOTTOM = "/square1_in_the_bottom_and_square2_3_pixel_above_bottom.txt";
    private static final String SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_2_PIXEL_ABOVE_BOTTOM_AND_2_PIXEL_LEFT = "/square1_in_the_bottom_and_square2_2_pixel_above_bottom_and_2_pixel_left.txt";
    private static final String SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_IN_THE_BOTTOM_AND_SQUARE3_STOPPED_ON_THEM = "/square1_in_the_bottom_and_square2_in_the_bottom_and_square3_stopped_on_them.txt";
    private static final String SQUARE_AT_THE_TOP_LEFT_CORNER = "/square_at_the_top_left_corner.txt";
    private static final String SQUARE_AT_THE_BOTTOM_LEFT_CORNER = "/square_at_the_bottom_left_corner.txt";
    private static final String SQUARE_AT_THE_BOTTOM_LEFT_CORNER_AND_ANOTHER_SQUARE_ON_IT = "/suqare_at_the_bottom_left_corner_and_another_square_on_it.txt";
    private static final String STICK_ON_TOP = "/stick_on_top.txt";
    private static final String STICK_ROTATED_1_PIXEL_BELOW_TOP = "/stick_rotated_1_pixel_below_top.txt";
    private static final String L_FIGURE_ON_TOP = "/L-figure_on_top.txt";
    private static final String L_FIGURE_IN_THE_BOTTOM = "/L-figure_in_the_bottom.txt";
    private static final String L_FIGURE_ROTATED_ONCE = "/L-figure_rotated_once_1pxbt.txt";
    private static final String L_FIGURE_ROTATED_ONCE_IN_THE_BOTTOM = "/L-figure_rotated_once_in_the_bottom.txt";
    private static final String L_FIGURE_ROTATED_THREE_TIMES_MOVED_LEFT_2PXBT = "/L-figure_rotated_three_times_moved_left_2pxbt.txt";
    private static final String L_FIGURE_ROTATED_THREE_TIMES_AND_MOVED_LEFT_IN_THE_BOTTOM = "/L-figure_rotated_three_times_and_moved_left_in_the_bottom.txt";
    private static final String L_FIGURE_ON_RIGHT_3PXBT = "/L-figure_on_right_3pxbt.txt";
    private static final String L_FIGURE_MOVED_RIGHT_IN_THE_BOTTOM = "/L-figure_moved_right_in_the_bottom.txt";
    private static final String STICK_NEAR_LEFT_BORDER_1PBT = "/stick_near_left_border_1pbt.txt";
    private static final String STICK_NEAR_RIGHT_BORDER_2PBT = "/stick_near_right_border_2pbt.txt";
    private static final String STICK_2PAB = "/stick_2pab.txt";
    private static final String AFTER_ROWS_DISAPPEARED = "/after_rows_disappeared.txt";
    private static final String STICK_1PBT_AND_BLOCKS_ON_TOP = "/stick_1pbt_and_blocks_on_top.txt";

    @Test
    public void shouldStartGameWithBlankScreen() throws Exception {
        click(START_BUTTON_ID);

        assertEquals(
                "Game screen on start wasn't blank",
                readFile(BLANK_PATH),
                getGameData());
    }

    @Test
    public void shouldShowSquareOnGameScreen() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        assertEquals(
                "Square hasn't appeared at the top",
                readFile(SQUARE_ON_TOP_PATH),
                getGameData());
    }

    @Test
    public void shouldShowFallingSquare() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        waitFor(stepsBeyond(1));
        assertEquals(
                "Square wasn't falling at expected speed",
                readFile(SQUARE_1_PIXEL_BELOW_TOP),
                getGameData());

        waitFor(steps(1));
        assertEquals(
                "Square wasn't falling at expected speed",
                readFile(SQUARE_2_PIXEL_BELOW_TOP),
                getGameData());
    }

    @Test
    public void shouldStopInTheBottom() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        assertEquals(
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
        assertEquals(
                "Square didn't stop in the bottom",
                readFile(SQUARE_IN_THE_BOTTOM),
                gameArray);
    }

    @Test
    public void shouldStopWhenReachesBlocks() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_IN_BOTTOM);
        click(TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM);

        assertEquals(
                "Initial state with figure near bottom and blocks in the bottom wasn't set",
                readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM_WITH_BLOCKS_IN_BOTTOM),
                getGameData());

        waitFor(stepsBeyond(2));
        assertEquals(
                "Square didn't stop when it reached blocks",
                readFile(SQUARE_STOPPED_AT_BLOCKS_1_PIXEL_ABOVE_BOTTOM),
                getGameData());
    }

    @Test
    public void shouldBecomeBlocksWhenFell() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_2_SQUARES_3_PIXEL_ABOVE_BOTTOM);

        waitFor(stepsBeyond(4));
        assertEquals(
                "Second square wasn't launched after first fell",
                readFile(SQUARE_IN_THE_BOTTOM_AND_SECOND_SQUARE_ABOVE_IT),
                getGameData());

        waitFor(steps(2));
        assertEquals(
                "Second square didn't fall on top of first one",
                readFile(SQUARE_ON_TOP_OF_ANOTHER_SQUARE_IN_THE_BOTTOM),
                getGameData()
        );
    }

    @Test
    public void shouldAutomaticallyLaunchNextFigureOnTop() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_SQUARE2_PIXEL_ABOVE_BOTTOM_WITH_FIGURE_PROVIDER);

        assertEquals(
                "Square should appear near bottom",
                readFile(SQUARE_2_PIXEL_ABOVE_BOTTOM),
                getGameData());

        waitFor(stepsBeyond(3));

        assertEquals(
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

        assertEquals(
                "Should appear 2 pixels below top",
                readFile(SQUARE_2_PIXEL_BELOW_TOP),
                getGameData()
        );

        click(TEST_SET_SPEED_5);
        waitFor(steps(3));

        assertEquals(
                "Should appear 5 pixels below top",
                readFile(SQUARE_5_PIXEL_BELOW_TOP),
                getGameData()
        );

        click(TEST_SET_SPEED_2);
        waitFor(steps(2));
        assertEquals(
                "Should appear 7 pixels below top",
                readFile(SQUARE_7_PIXEL_BELOW_TOP),
                getGameData()
        );
    }

    @Test
    public void shouldGoRightAndLeft() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_THREE_SQUARES_3_PIXEL_ABOVE_BOTTOM);
        click(GO_RIGHT);
        waitFor(stepsBeyond(0));

        assertEquals(
                "Figure should be 3 pixels above bottom and 1 pixel right",
                readFile(SQUARE_3_PIXEL_ABOVE_BOTTOM_1_PIXEL_RIGHT),
                getGameData()
        );

        waitFor(steps(3));

        assertEquals(
                "Figure should be in the bottom and 1 pixel right",
                readFile(SQUARE_IN_THE_BOTTOM_AND_1_PIXEL_RIGHT),
                getGameData()
        );

        waitFor(steps(1));

        assertEquals(
                "First figure should be in the bottom and the second figure should be 3 pixels above bottom",
                readFile(SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_3_PIXEL_ABOVE_BOTTOM),
                getGameData()
        );

        click(GO_LEFT);
        click(GO_LEFT);
        waitFor(steps(1));

        assertEquals(
                "First figure should be in the bottom and the second figure should be 2 pixels above bottom and 2 pixels left",
                readFile(SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_2_PIXEL_ABOVE_BOTTOM_AND_2_PIXEL_LEFT),
                getGameData()
        );

        waitFor(steps(5));

        assertEquals(
                "First figure should be in the bottom and the second figure should be in the bottom and third \n" +
                "square stopped at blocks",
                readFile(SQUARE1_IN_THE_BOTTOM_AND_SQUARE2_IN_THE_BOTTOM_AND_SQUARE3_STOPPED_ON_THEM),
                getGameData()
        );
    }

    @Test
    public void shouldInstantlyDropFigure() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_TWO_SQUARES_ON_TOP);

        click(GO_LEFT);
        click(GO_LEFT);
        click(GO_LEFT);
        click(GO_LEFT);
        click(GO_BOTTOM);
        waitFor(stepsBeyond(0));

        assertEquals(
                "Square should be at the bottom left corner\n" + getGameData(),
                readFile(SQUARE_AT_THE_BOTTOM_LEFT_CORNER),
                getGameData()
        );

        waitFor(steps(1));
        click(GO_LEFT);
        click(GO_LEFT);
        click(GO_LEFT);
        click(GO_BOTTOM);

        assertEquals(
                "Second square should be on the first square near bottom left corner",
                readFile(SQUARE_AT_THE_BOTTOM_LEFT_CORNER_AND_ANOTHER_SQUARE_ON_IT),
                getGameData()
        );
    }

    @Test
    public void shouldRotateStickFigure() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_STICK);
        waitFor(stepsBeyond(0));

        assertEquals(
                "A stick figure should appear on top",
                readFile(STICK_ON_TOP),
                getGameData()
        );

        waitFor(steps(1));
        click(DO_ROTATE);

        assertEquals(
                "The stick figure should be rotated",
                readFile(STICK_ROTATED_1_PIXEL_BELOW_TOP),
                getGameData()
        );
    }

    @Test
    public void shouldRotateAndMoveLFigureWithGhost() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_LAUNCH_L_FIGURE);
        waitFor(stepsBeyond(0));

        assertEquals(
                "L-Figure should appear on top",
                readFile(L_FIGURE_ON_TOP),
                getGameData()
        );
        assertEquals(
                "L-Figure ghost should appear in the bottom",
                readFile(L_FIGURE_IN_THE_BOTTOM),
                getGameGhost()
        );

        click(DO_ROTATE);
        waitFor(steps(1));

        assertEquals(
                "L-figure should rotate",
                readFile(L_FIGURE_ROTATED_ONCE),
                getGameData()
        );
        assertEquals(
                "L-figure ghost should change after one rotate",
                readFile(L_FIGURE_ROTATED_ONCE_IN_THE_BOTTOM),
                getGameGhost()
        );

        click(GO_LEFT);
        click(DO_ROTATE);
        click(DO_ROTATE);
        waitFor(steps(1));

        assertEquals(
                "L-figure should be moved left and rotated three times",
                readFile(L_FIGURE_ROTATED_THREE_TIMES_MOVED_LEFT_2PXBT),
                getGameData()
        );
        assertEquals(
                "L-figure ghost should change after left move",
                readFile(L_FIGURE_ROTATED_THREE_TIMES_AND_MOVED_LEFT_IN_THE_BOTTOM),
                getGameGhost()
        );

        click(GO_RIGHT);
        click(GO_RIGHT);
        click(DO_ROTATE);
        waitFor(steps(1));

        assertEquals(
                "L-figure should be moved left and rotated full circle",
                readFile(L_FIGURE_ON_RIGHT_3PXBT),
                getGameData()
        );
        assertEquals(
                "L-figure ghost should be moved right",
                readFile(L_FIGURE_MOVED_RIGHT_IN_THE_BOTTOM),
                getGameGhost()
        );
    }

    @Test
    public void stickFigureShouldNotRotateNearBlocksAndBoundaries() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_ON_RIGHT_SIDE);
        click(TEST_LAUNCH3_STICKS_NEAR_BOUNDARIES);
        waitFor(stepsBeyond(1));
        click(DO_ROTATE);

        assertEquals(
                "Stick should not rotate near left border",
                readFile(STICK_NEAR_LEFT_BORDER_1PBT),
                getGameData()
        );

        click(GO_BOTTOM);
        waitFor(steps(1));
        click(DO_ROTATE);

        assertEquals(
                "Stick should not rotate near right border",
                readFile(STICK_NEAR_RIGHT_BORDER_2PBT),
                getGameData()
        );

        click(GO_BOTTOM);
        waitFor(steps(1));
        click(DO_ROTATE);
        waitFor(steps(1));
        click(DO_ROTATE);

        assertEquals(
                "Stick should not rotate near the bottom",
                readFile(STICK_2PAB),
                getGameData()
        );
    }

    @Test
    public void shouldEraseBlocksWhenRowFilled() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_FOR_ERASURE);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        click(GO_BOTTOM);
        waitFor(stepsBeyond(1));

        assertEquals(
                "Filled rows should disappear",
                readFile(AFTER_ROWS_DISAPPEARED),
                getGameData()
        );
    }

    @Test
    public void figureShouldNotMoveIntoBlocks() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_ON_TOP);
        click(TEST_LAUNCH_STICK);

        click(GO_RIGHT);
        waitFor(stepsBeyond(1));

        assertEquals(
                "Figure should not go into blocks",
                readFile(STICK_1PBT_AND_BLOCKS_ON_TOP),
                getGameData()
        );
    }

    @Test
    public void shouldGainPoints() throws Exception {
        click(START_BUTTON_ID);
        click(TEST_SET_BLOCKS_FOR_SCORE_GAIN);
        click(TEST_LAUNCH_SQUARE_BUTTON);

        waitFor(stepsBeyond(0));
        click(GO_BOTTOM);
        waitFor(steps(1));

        assertEquals(
                "Game score should increase",
                GameScreen.SCORE_FOR_2_ROWS,
                getCurrentScore()
        );
    }

    private int getCurrentScore() {
        Label scoreLabel = find("#currentScoreLabel");
        return Integer.parseInt(scoreLabel.getText());
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

    private String getGameGhost() {
        int[][] ghostArray = getGhostArrayFromScreen();
        return stringifyScreenArray(ghostArray);
    }

    private String getGameData() {
        int[][] gameArray = getBlockArrayFromScreen();
        return stringifyScreenArray(gameArray);
    }

    private int[][] getGhostArrayFromScreen() {
        return getArrayFromScreen(UIBuilder.GHOST_COLOR);
    }

    private int[][] getBlockArrayFromScreen() {
        return getArrayFromScreen(UIBuilder.BLOCK_COLOR);
    }

    private int[][] getArrayFromScreen(Color pixelColor) {
        int screenArray[][] = new int[GAME_SCREEN_HEIGHT][GAME_SCREEN_WIDTH];

        ObservableList<Node> children = findGameScreen().getChildren();
        for (Node aChildren : children) {
            Rectangle cell = (Rectangle) aChildren;
            if (cell.getFill().equals(pixelColor)) {
                screenArray[GridPane.getRowIndex(cell)][GridPane.getColumnIndex(cell)] = 1;
            }
        }
        return screenArray;
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

    private static String stringifyScreenArray(int[][] screenArray) {
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
