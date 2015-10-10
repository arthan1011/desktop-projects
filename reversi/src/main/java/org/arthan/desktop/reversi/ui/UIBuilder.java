package org.arthan.desktop.reversi.ui;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.effect.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        return new ReversiSquare(x, y);
    }

    private static class ReversiSquare extends Region {
        public ReversiSquare() {
            setStyle("-fx-background-color: burlywood");
            Light.Distant light = new Light.Distant();
            light.setAzimuth(-135);
            light.setElevation(30);

            Lighting lighting = new Lighting(light);
            setEffect(lighting);
        }

        public ReversiSquare(int x, int y) {
            this();
            styleProperty().bind(Bindings.when(ReversiModel.getInstance().legalMove(x, y))
                    .then("-fx-background-color: derive(dodgerblue, -60%)")
                    .otherwise("-fx-background-color: burlywood")
            );
        }
    }
}
