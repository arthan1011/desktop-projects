package org.arthan.desktop.reversi.ui;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.effect.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.arthan.desktop.reversi.model.OWNER;
import org.arthan.desktop.reversi.model.ReversiModel;
import org.arthan.desktop.reversi.model.ReversiPiece;

/**
 * Created by ashamsiev on 07.10.2015
 */
public class UIBuilder {
    public static Region getReversiSquare() {
        return new ReversiSquare();
    }

    public static Region getReversiSquare(int x, int y) {
        ReversiSquare reversiSquare = new ReversiSquare(x, y);

        return reversiSquare;
    }

    private static class ReversiSquare extends Region {

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
            styleProperty().bind(Bindings.when(ReversiModel.getInstance().legalMove(x, y))
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
        }
    }
}
