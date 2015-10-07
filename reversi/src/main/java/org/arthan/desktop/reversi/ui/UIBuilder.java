package org.arthan.desktop.reversi.ui;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
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

    private StackPane createScore(OWNER owner) {
        Region background;
        Ellipse piece = new Ellipse(32, 20);
        piece.setFill(owner.getColor());
        DropShadow pieceEffect = new DropShadow();
        pieceEffect.setColor(Color.DODGERBLUE);
        pieceEffect.setSpread(.2);
        piece.setEffect(pieceEffect);

        Text score = new Text();
        score.setFont(Font.font(null, FontWeight.BOLD, 100));
        score.setFill(owner.getColor());

        Text remaining = new Text();
        remaining.setFont(Font.font(null, FontWeight.BOLD, 12));
        remaining.setFill(owner.getColor());
        VBox remainngBox = new VBox(10, piece, remaining);
        remainngBox.setAlignment(Pos.CENTER);

        FlowPane flowPane = new FlowPane(20, 10, score, remainngBox);
        flowPane.setAlignment(Pos.CENTER);

        background = new Region();
        background.setStyle("-fx-background-color: " + owner.opposite().getColorStyle());

        ReversiModel model = ReversiModel.getInstance();

        StackPane stack = new StackPane(background, flowPane);
        stack.setPrefHeight(1000);

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.DODGERBLUE);
        innerShadow.setChoke(.2);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DODGERBLUE);
        dropShadow.setSpread(.2);

        piece.effectProperty().bind(Bindings.when(model.turn.isEqualTo(owner))
                .then(dropShadow)
                .otherwise((DropShadow)null));
        score.textProperty().bind(model.getScore(owner).asString());
        remaining.textProperty().bind(model.getTurnsRemaining(owner).asString().concat(" turns remaining"));

        return stack;
    }
}
