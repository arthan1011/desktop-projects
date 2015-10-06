package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by ashamsiev on 06.10.2015
 */
public class StackApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text("New App");
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BOLD, 18));
        text.setFill(Color.WHITE);

        Ellipse ellipse = new Ellipse();
        ellipse.setFill(Color.GREEN);

        StackPane stackPane = new StackPane(ellipse, text);

        Scene scene = new Scene(stackPane, 400, 100);

        ellipse.radiusXProperty().bind(scene.widthProperty().divide(2));
        ellipse.radiusYProperty().bind(scene.heightProperty().divide(2));

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }
}
