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

/**
 * Created by ashamsiev on 07.10.2015
 */
public class UIBuilder {
    public static Region getReversiSquare() {
        return new ReversiSquare();
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
    }
}
