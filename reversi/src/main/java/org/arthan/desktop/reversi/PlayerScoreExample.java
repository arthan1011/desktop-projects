package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import org.arthan.desktop.reversi.model.OWNER;
import org.arthan.desktop.reversi.ui.UIBuilder;

/**
 * Created by Arthur Shamsiev on 07.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class PlayerScoreExample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        UIBuilder uiBuilder = new UIBuilder();

        TilePane tilePane = new TilePane();
        tilePane.setSnapToPixel(false);
        tilePane.getChildren().addAll(
                uiBuilder.createScore(OWNER.BLACK),
                uiBuilder.createScore(OWNER.WHITE));

        Scene scene = new Scene(tilePane, 800, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
