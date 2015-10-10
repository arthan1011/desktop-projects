package org.arthan.desktop.reversi.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Region;
import org.arthan.desktop.reversi.model.OWNER;

/**
 * Created by Arthur Shamsiev on 10.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class ReversiPiece extends Region {
    private ObjectProperty<OWNER> ownerProperty = new SimpleObjectProperty<>(this, "owner", OWNER.NONE);

    public ObjectProperty<OWNER> ownerProperty() {
        return ownerProperty;
    }

    public OWNER getOwner() {
        return ownerProperty.get();
    }

    public void setOwner(OWNER owner) {
        ownerProperty.set(owner);
    }

    public ReversiPiece() {
        styleProperty().bind(Bindings.when(ownerProperty.isEqualTo(OWNER.NONE))
                .then("radius 0")
                .otherwise(Bindings.when(ownerProperty.isEqualTo(OWNER.WHITE))
                        .then("-fx-background-color: radial-gradient(radius 100%, white .4, gray .9, darkgray 1)")
                        .otherwise("-fx-background-color: radial-gradient(radius 100%, white 0, black .6)"))
                .concat("; -fx-background-radius: 1000em; -fx-background-insets: 5"));

        Reflection reflection = new Reflection();
        reflection.setFraction(1);
        reflection.topOffsetProperty().bind(heightProperty().multiply(-.75));
        setEffect(reflection);
        setPrefSize(80, 80);
        setMouseTransparent(true);
    }

    public ReversiPiece(OWNER owner) {
        this();
        ownerProperty.setValue(owner);
    }
}
