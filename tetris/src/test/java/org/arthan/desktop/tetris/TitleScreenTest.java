package org.arthan.desktop.tetris;

import javafx.scene.input.MouseButton;
import org.junit.Test;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TitleScreenTest extends TestGui {
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
