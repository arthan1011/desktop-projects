package org.arthan.desktop.reversi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;

/**
 * Created by ashamsiev on 15.10.2015
 */
public class ReversiController {

    @FXML
    TilePane titlePane;
    @FXML
    StackPane centerPane;
    @FXML
    TilePane scorePane;
    @FXML
    StackPane leftScore;
    @FXML
    StackPane rightScore;
    @FXML
    Region whiteRegion;
    @FXML
    Region blackRegion;
    @FXML
    Text scoreBlack;
    @FXML
    Text scoreWhite;
    @FXML
    Ellipse blackEllipse;
    @FXML
    Ellipse whiteEllipse;
    @FXML
    Text remainingBlack;
    @FXML
    Text remainingWhite;

    @FXML
    public void restart() {

    }
}
