package org.arthan.desktop.reversi;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Created by ashamsiev on 13.11.2015
 */
public class StarsApp extends Application {

    private static final int STAR_COUNT = 20000;
    private static final int DURATION_NANO = 2000000000;

    private final Rectangle[] nodes = new Rectangle[STAR_COUNT];
    private final double[] angles = new double[STAR_COUNT];
    private final long[] start = new long[STAR_COUNT];

    private final Random random = new Random(47);

    @Override
    public void start(Stage primaryStage) throws Exception {

        for (int i = 0; i < STAR_COUNT; i++) {
            nodes[i] = new Rectangle(2, 1, Color.AQUA);
            angles[i] = 2.0 * Math.PI * random.nextDouble();
            start[i] = random.nextInt(DURATION_NANO);
        }

        final Scene scene = new Scene(new Group(nodes), 800, 700, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                final double width = primaryStage.getWidth() * 0.5;
                final double height = primaryStage.getHeight() * 0.5;
                final double radius = Math.sqrt(2) * Math.max(width, height);
                for (int i = 0; i < STAR_COUNT; i++) {
                    final Node node = nodes[i];
                    final double angle = angles[i];
                    final long time = (now - start[i]) % DURATION_NANO;
                    final double distance = time * radius / (DURATION_NANO * 1.0);
                    node.setTranslateX(Math.cos(angle) * distance + width);
                    node.setTranslateY(Math.sin(angle) * distance + height);
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
