package org.arthan.desktop.tetris.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.util.UIBuilder;

import static org.arthan.desktop.tetris.util.UIBuilder.createPixel;

/**
 * Created by Arthur Shamsiev on 05.12.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class GameScreenController {

    @FXML
    public GridPane gameScreen;

    @FXML
    public void launchSquare(ActionEvent event) {
        gameScreen.add(createPixel(), 4, 0);
        gameScreen.add(createPixel(), 5, 0);
        gameScreen.add(createPixel(), 4, 1);
        gameScreen.add(createPixel(), 5, 1);
    }

}
