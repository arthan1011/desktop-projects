package org.arthan.desktop.reversi.model;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.util.Duration;

/**
 * Created by Arthur Shamsiev on 17.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class ReversiSquare extends Region {

    private Region highlight = RegionBuilder.create()
            .opacity(0)
            .style("-fx-border-width: 3; -fx-border-color: dodgerblue")
            .build();

    private FadeTransition hightlightTransition = FadeTransitionBuilder.create()
            .node(highlight)
            .duration(Duration.millis(200))
            .fromValue(0)
            .toValue(1)
            .build();

    private ReversiModel model = ReversiModel.getInstance();

    public ReversiSquare() {
        setStyle("-fx-background-color: burlywood");
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135);
        light.setElevation(30);

        getChildren().add(highlight);

        Lighting lighting = new Lighting(light);
        setEffect(lighting);
    }

    @Override
    protected void layoutChildren() {
        layoutInArea(highlight, 0, 0, getWidth(), getHeight(), getBaselineOffset(), HPos.CENTER, VPos.CENTER);
    }

    public ReversiSquare(int x, int y) {
        this();
        styleProperty().bind(Bindings.when(model.legalMove(x, y))
                        .then("-fx-background-color: derive(dodgerblue, -60%)")
                        .otherwise("-fx-background-color: burlywood")
        );

        addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, t -> {
            if (model.legalMove(x, y).get()) {
                hightlightTransition.setRate(1);
                hightlightTransition.play();
            }
        });

        addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, t -> {
            if (model.legalMove(x, y).get()) {
                hightlightTransition.setRate(-1);
                hightlightTransition.play();
            }
        });

        setOnMouseClicked(t -> {
            model.play(x, y);
            hightlightTransition.setRate(-1);
            hightlightTransition.play();
        });
    }
}
