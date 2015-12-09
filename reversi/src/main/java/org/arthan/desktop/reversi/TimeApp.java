package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;

/**
 * Created by ashamsiev on 06.10.2015
 */
public class TimeApp extends Application {

    private Model model;

    public TimeApp() {
        this.model = new Model();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label timeLabel = new Label();
        timeLabel.textProperty().bind(model.worker.messageProperty());

        HBox hBox = new HBox(new Label("Time: "), timeLabel);

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> new Thread((Runnable) model.worker).start());

        VBox vBox = new VBox(hBox, startButton);

        Scene scene = new Scene(vBox, 400, 100);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(100);
        primaryStage.show();
    }

    private static class Model {
        public Worker<String> worker;

        public Model() {
            worker = new Task<String>() {
                @Override
                protected String call() throws Exception {
                    while (true) {
                        LocalTime now = LocalTime.now();
                        updateMessage(now.toString());
                        Thread.sleep(200);
                        System.out.println("in worker thread!");
                        if (isCancelled())
                            return null;
                    }
                }
            };
        }
    }
}
