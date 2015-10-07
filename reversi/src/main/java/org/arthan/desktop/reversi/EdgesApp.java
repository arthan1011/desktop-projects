package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by ashamsiev on 06.10.2015
 */
public class EdgesApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane left = new StackPane();
        left.setStyle("-fx-background-color: black");
        Text javaFXText = new Text("JavaFX");
        javaFXText.setFont(Font.font(null, FontWeight.BOLD, 18));
        javaFXText.setFill(Color.WHITE);
        StackPane.setAlignment(javaFXText, Pos.CENTER_RIGHT);
        left.getChildren().add(javaFXText);

        Text reversiText = new Text("Reversi");
        reversiText.setFont(Font.font(null, FontWeight.BOLD, 18));
        TilePane tilePane = new TilePane();
        tilePane.setSnapToPixel(false);
        TilePane.setAlignment(reversiText, Pos.CENTER_LEFT);
        tilePane.getChildren().addAll(left, reversiText);

        Scene scene = new Scene(tilePane, 400, 100);

        left.prefWidthProperty().bind(tilePane.widthProperty().divide(2));
        left.prefHeightProperty().bind(tilePane.heightProperty());

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }
}
