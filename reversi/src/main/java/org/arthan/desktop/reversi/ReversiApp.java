package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.arthan.desktop.reversi.model.ReversiModel;
import org.arthan.desktop.reversi.service.RemoteService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arthur Shamsiev on 07.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class ReversiApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ReversiApp.class.getResource("/reversi.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }


}
