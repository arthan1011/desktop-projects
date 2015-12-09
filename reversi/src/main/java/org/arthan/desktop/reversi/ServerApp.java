package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.arthan.desktop.reversi.server.ReversiServer;

/**
 * Created by ashamsiev on 06.10.2015
 */
public class ServerApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ReversiServer.createServer(Config.getServerPort());
    }
}
