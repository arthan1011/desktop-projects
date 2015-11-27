package org.arthan.desktop.tetris.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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

    public void initialize() {
        System.out.println("title screen controller initialized");
    }
}
