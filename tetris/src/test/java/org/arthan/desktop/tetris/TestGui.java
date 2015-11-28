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
    protected static final String START_BUTTON_ID = "#startButton";
    protected static final String EXIT_BUTTON_ID = "#exitButton";
    protected static final String GAME_SCREEN_ID = "#gameScreen";

    protected String readFile(String path) {
        String expectedScreenArray;
        try {
            expectedScreenArray = FileUtils.readFileToString(new File(TestGui.class.getResource(path).toURI()));
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
