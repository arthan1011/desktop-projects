package org.arthan.desktop.tetris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.arthan.desktop.tetris.util.UIBuilder;

/**
 * Created by Arthur Shamsiev on 21.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class TetrisApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(UIBuilder.createRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
