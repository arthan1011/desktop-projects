package org.arthan.desktop.tetris.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.arthan.desktop.tetris.util.UIBuilder;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TitleScreenController {

    @FXML
    public Button exitButton;

    @FXML
    public void exit() {
        Platform.exit();
    }

    @FXML
    public void startGame(ActionEvent event) {
        Pane tetrisAppRoot = getRootFromEvent(event);

        tetrisAppRoot.getChildren().clear();
        tetrisAppRoot.getChildren().add(UIBuilder.createGameScreen());
        Stage window = (Stage) tetrisAppRoot.getScene().getWindow();
        window.setHeight(600);
        window.setWidth(800);
    }

    private Pane getRootFromEvent(ActionEvent event) {
        return (Pane) ((Node)event.getSource()).getScene().getRoot();
    }
}
