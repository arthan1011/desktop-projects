package org.arthan.desktop.reversi.controller;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.arthan.desktop.reversi.Config;
import org.arthan.desktop.reversi.model.*;
import org.arthan.desktop.reversi.server.ReversiClient;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ashamsiev on 15.10.2015
 */
public class ReversiController {


    private ReversiModel model = ReversiModel.getInstance();
    @FXML
    TilePane titlePane;
    @FXML
    StackPane centerPane;
    @FXML
    TilePane scorePane;

    @FXML
    StackPane leftScore;
    @FXML
    StackPane rightScore;
    @FXML
    Region whiteRegion;
    @FXML
    Region blackRegion;
    @FXML
    Text scoreBlack;
    @FXML
    Text scoreWhite;
    @FXML
    Ellipse blackEllipse;
    @FXML
    Ellipse whiteEllipse;
    @FXML
    Text remainingBlack;
    @FXML
    Text remainingWhite;
    private static ReversiClient reversiClient;

    public void initialize() {
        // top and bottom sizing
        titlePane.prefTileWidthProperty().bind(Bindings.selectDouble(titlePane.parentProperty(), "width").divide(2));
        scorePane.prefTileWidthProperty().bind(Bindings.selectDouble(scorePane.parentProperty(), "width").divide(2));

        // set board
        centerPane.getChildren().add(tiles());
        StackPane maskPane = maskPane();
        maskPane.visibleProperty().bind(model.turn.isNotEqualTo(Config.getPlayerColor()));
        maskPane.setCursor(Cursor.WAIT);
        centerPane.getChildren().add(maskPane);

        // score bindings
        scoreBlack.textProperty().bind(model.getScore(OWNER.BLACK).asString());
        scoreWhite.textProperty().bind(model.getScore(OWNER.WHITE).asString());

        // remaining turns bindings
        remainingBlack.textProperty().bind(model.getTurnsRemaining(OWNER.BLACK).asString());
        remainingWhite.textProperty().bind(model.getTurnsRemaining(OWNER.WHITE).asString());

        // player turn highlight binding
        InnerShadow innerShadow = createInnerShadow();
        whiteRegion.effectProperty().bind(Bindings.when(model.turn.isEqualTo(OWNER.WHITE))
                .then(innerShadow)
                .otherwise((InnerShadow) null));
        blackRegion.effectProperty().bind(Bindings.when(model.turn.isEqualTo(OWNER.BLACK))
                .then(innerShadow)
                .otherwise((InnerShadow) null));
        DropShadow dropShadow = createDropShadow();
        blackEllipse.setEffect(dropShadow);
        blackEllipse.effectProperty().bind(Bindings.when(model.turn.isEqualTo(OWNER.BLACK))
                        .then(dropShadow)
                        .otherwise((DropShadow) null)
        );
        whiteEllipse.effectProperty().bind(Bindings.when(model.turn.isEqualTo(OWNER.WHITE))
                .then(dropShadow)
                .otherwise((DropShadow) null)
        );

        checkUpdates(model);
    }

    private StackPane maskPane() {
        Text maskText = new Text("Ожидание хода соперника");
        maskText.setFont(Font.font("Arial", FontWeight.BOLD, 44));
        maskText.setStroke(Color.WHITE);
        maskText.setStrokeType(StrokeType.OUTSIDE);
        maskText.setStrokeWidth(1.8);
        return new StackPane(maskText);
    }

    private static void checkUpdates(ReversiModel model) {
        reversiClient = ReversiClient.getInstance();
        Timer timer = new Timer(true);
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            if (model.turn.get() != Config.getPlayerColor()) {
                                System.out.println("checking for updates..");
                                GameInfo gameInfo = reversiClient.retriveInfo();
                                model.applyGameInfo(gameInfo);
                            }
                        });
                    }
                },
                0,
                1000
        );
    }

    private DropShadow createDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.DODGERBLUE);
        dropShadow.setSpread(.2);
        return dropShadow;
    }

    private InnerShadow createInnerShadow() {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setColor(Color.DODGERBLUE);
        innerShadow.setChoke(.5);
        return innerShadow;
    }

    private Node tiles() {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < ReversiModel.BOARD_SIZE; i++) {
            for (int j = 0; j < ReversiModel.BOARD_SIZE; j++) {
                ReversiPiece piece = new ReversiPiece();
                Region square = new ReversiSquare(i, j);
                piece.ownerProperty().bind(model.board[i][j]);
                gridPane.add(new StackPane(square, piece), i, j);
            }
        }
        return gridPane;
    }

    @FXML
    public void restart() {
        model.applyGameInfo(reversiClient.reset());
    }
}
