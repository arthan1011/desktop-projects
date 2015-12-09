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

    public static final String SQUARE_ON_TOP_PATH = "/square_on_top.txt";
    private static final String BLANK_PATH = "/blank.txt";
    public static final String SQUARE_1_PIXEL_BELOW_TOP = "/square_1_pixel_below_top.txt";

    @Test
    public void shouldStartGameWithBlankScreen() throws Exception {
        click(START_BUTTON_ID, MouseButton.PRIMARY);
        GridPane screen = find(GAME_SCREEN_ID);

        int[][] screenArray = getArrayFromScreen(screen);

        String actualScreenArray = stringifyScreenArray(screenArray);
        String expectedScreenArray = readFile(BLANK_PATH);

        Assert.assertEquals("Game screen on start wasn't blank", expectedScreenArray, actualScreenArray);
    }

    @Test
    public void shouldShowSquareOnGameScreen() throws Exception {
        click(START_BUTTON_ID, MouseButton.PRIMARY);
        click(TEST_LAUNCH_SQUARE_BUTTON, MouseButton.PRIMARY);
        GridPane screen = find(GAME_SCREEN_ID);

        int[][] screenArray = getArrayFromScreen(screen);

        String actualScreenArray = stringifyScreenArray(screenArray);
        String expectedScreenArray = readFile(SQUARE_ON_TOP_PATH);

        Assert.assertEquals("Square hasn't appeared at the top", expectedScreenArray, actualScreenArray);
    }

    @Test
    public void shouldShowFallingSquare() throws Exception {
        click(START_BUTTON_ID, MouseButton.PRIMARY);
        click(TEST_LAUNCH_SQUARE_BUTTON, MouseButton.PRIMARY);
        waitFor(1500);
        GridPane screen = find(GAME_SCREEN_ID);

        int[][] gameArray = getArrayFromScreen(screen);
        String actualScreenArray = stringifyScreenArray(gameArray);
        String expectedScreenArray = readFile(SQUARE_1_PIXEL_BELOW_TOP);

        Assert.assertEquals("Square wasn't falling at expected speed", expectedScreenArray, actualScreenArray);
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
