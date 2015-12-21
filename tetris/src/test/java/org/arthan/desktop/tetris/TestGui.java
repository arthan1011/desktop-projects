package org.arthan.desktop.tetris;

import javafx.scene.Parent;
import org.apache.commons.io.FileUtils;
import org.arthan.desktop.tetris.util.UIBuilder;
import org.loadui.testfx.GuiTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Arthur Shamsiev on 28.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TestGui extends GuiTest {
    public static final String TEST_LAUNCH_SQUARE_BUTTON = "#test_LaunchSquareButton";
    public static final String TEST_SET_BLOCKS_IN_BOTTOM = "#test_set_blocks_in_bottom";
    public static final String TEST_LAUNCH_2_SQUARES_3_PIXEL_ABOVE_BOTTOM = "#test_launch2Squares3PixelAboveBottom";
    public static final String TEST_SET_SPEED_5 = "#test_setSpeed_5";
    public static final String TEST_SET_SPEED_2 = "#test_setSpeed_2";
    public static final String TEST_LAUNCH_THREE_SQUARES_3_PIXEL_ABOVE_BOTTOM = "#test_launch_three_squares_3_pixel_above_bottom";
    public static final String GO_RIGHT = "#goRight";
    protected static final String CURRENT_SPEED_LABEL = "#currentSpeedLabel";
    protected static final String START_BUTTON_ID = "#startButton";
    protected static final String EXIT_BUTTON_ID = "#exitButton";
    protected static final String GAME_SCREEN_ID = "#gameGrid";
    protected static final String TEST_LAUNCH_SQUARE_BUTTON_NEAR_BOTTOM = "#test_LaunchSquareNearBottomButton";
    protected static final String TEST_LAUNCH_SQUARE2_PIXEL_ABOVE_BOTTOM_WITH_FIGURE_PROVIDER = "#testLaunchSquare2PixelAboveBottom__withFigureProvider";
    protected static final String GO_LEFT = "#goLeft";
    protected static final String TEST_LAUNCH_TWO_SQUARES_ON_TOP = "#test_launch_two_squares_on_top";
    static final String GO_BOTTOM = "#goBottom";
    protected static final String TEST_LAUNCH_STICK = "#test_launchStick";
    protected static final String DO_ROTATE = "#doRotate";

    protected String readFile(String path) {
        String expectedScreenArray;
        try {
            expectedScreenArray = FileUtils.readFileToString(new File(TestGui.class.getResource(path).toURI()));
            // code need to be cross-platform
            expectedScreenArray = expectedScreenArray.replace("\r", "");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return expectedScreenArray;
    }

    @Override
    protected Parent getRootNode() {
        return UIBuilder.createRoot();
    }
}
