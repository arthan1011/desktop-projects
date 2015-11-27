package org.arthan.desktop.tetris.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class UILoader {

    public static final String GAME_SCREEN_FXML_PATH = "/game_screen.fxml";
    public static final String GAME_SCREEN_FXML_ERROR_MESSAGE = "game screen fxml was not loaded";
    public static final String TITLE_SCREEN_FXML_PATH = "/title_screen.fxml";
    public static final String TITLE_SCREEN_FXML_ERROR_MESSAGE = "title_screen.fxml was not loaded";

    static BorderPane loadGameScreen() {
        Parent parentNode = load(GAME_SCREEN_FXML_PATH, GAME_SCREEN_FXML_ERROR_MESSAGE);
        return (BorderPane) parentNode;
    }

    static Parent loadTitleScreen() {
        return load(TITLE_SCREEN_FXML_PATH, TITLE_SCREEN_FXML_ERROR_MESSAGE);
    }

    private static Parent load(String fxmlPath, String errorMessage) {
        FXMLLoader loader = new FXMLLoader(UIBuilder.class.getResource(fxmlPath));
        Parent parentNode;
        try {
            parentNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(errorMessage, e);
        }
        return parentNode;
    }
}
