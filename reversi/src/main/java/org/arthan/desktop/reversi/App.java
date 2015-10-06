package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by ashamsiev on 06.10.2015
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Text text = new Text("New App");
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BOLD, 18));

        Scene scene = new Scene(new Group(text), 400, 100);

        text.layoutXProperty().bind(scene.widthProperty().subtract(text.prefWidth(-1)).divide(2));
        text.layoutYProperty().bind(scene.heightProperty().subtract(text.prefHeight(-1)).divide(2));

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }
}
