package org.arthan.desktop.tetris;

import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import org.arthan.desktop.tetris.util.UIBuilder;
import org.junit.Test;
import org.loadui.testfx.GuiTest;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TitleScreenTest extends GuiTest {

    public static final String START_BUTTON_ID = "#startButton";
    public static final String EXIT_BUTTON_ID = "#exitButton";
    public static final String GAME_SCREEN_ID = "#gameScreen";

    @Override
    protected Parent getRootNode() {
        return UIBuilder.createRoot();
    }

    @Test
    public void shouldHaveButtonNamedExit() throws Exception {
        find(EXIT_BUTTON_ID);
    }

    @Test
    public void shouldHaveStartButton() throws Exception {
        find(START_BUTTON_ID);
    }

    @Test
    public void shouldSwitchToGameScreenOnStartClick() throws Exception {
        click(START_BUTTON_ID, MouseButton.PRIMARY);
        find(GAME_SCREEN_ID);
    }
}
