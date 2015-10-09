package org.arthan.desktop.reversi;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.arthan.desktop.reversi.model.OWNER;
import org.arthan.desktop.reversi.model.ReversiModel;
import org.arthan.desktop.reversi.ui.UIBuilder;

/**
 * Created by Arthur Shamsiev on 07.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class ReversiApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setCenter(createBackground());
        root.setTop(createTitle());
        root.setBottom(createScoreBoxes());

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Node createBackground() {
        Region answer = new Region();
        answer.setStyle("-fx-background-color: radial-gradient(radius 100%, white, gray)");
        return UIBuilder.getReversiSquare();
    }

    private Node createTitle() {
        StackPane left = new StackPane();
        left.setStyle("-fx-background-color: black");
        Text javaFXText = new Text("JavaFX");
        javaFXText.setFont(Font.font(null, FontWeight.BOLD, 18));
        javaFXText.setFill(Color.WHITE);
        StackPane.setAlignment(javaFXText, Pos.CENTER_RIGHT);
        left.getChildren().add(javaFXText);

        Text reversiText = new Text("Reversi");
        reversiText.setFont(Font.font(null, FontWeight.BOLD, 18));

        TilePane.setAlignment(reversiText, Pos.CENTER_LEFT);
        TilePane titleTiles = new TilePane();
        titleTiles.setSnapToPixel(false);
        titleTiles.getChildren().addAll(left, reversiText);
        titleTiles.setPrefTileHeight(40);
        titleTiles.prefTileWidthProperty().bind(Bindings.selectDouble(titleTiles.parentProperty(), "width").divide(2));
        return titleTiles;

    }

    private Node createScoreBoxes() {
        TilePane scoreTiles = new TilePane(
                createScore(OWNER.BLACK),
                createScore(OWNER.WHITE));
        scoreTiles.setSnapToPixel(false);
        scoreTiles.setPrefColumns(2);
        scoreTiles.prefTileWidthProperty().bind(Bindings.selectDouble(scoreTiles.parentProperty(), "width").divide(2));
        return scoreTiles;
    }


    private StackPane createScore(OWNER owner) {
        Region background;
        Ellipse piece = new Ellipse(32, 20);
        piece.setFill(owner.getColor());
        DropShadow pieceEffect = new DropShadow();
        pieceEffect.setColor(Color.DODGERBLUE);
        pieceEffect.setSpread(.2);
        piece.setEffect(pieceEffect);

        Text score = new Text();
        score.setFont(Font.font("arial", FontWeight.BOLD, 100));
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
        stack.setPrefHeight(100);

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.DODGERBLUE);
        innerShadow.setChoke(.2);

        background.effectProperty().bind(Bindings.when(model.turn.isEqualTo(owner))
                .then(innerShadow)
                .otherwise((InnerShadow)null));

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
