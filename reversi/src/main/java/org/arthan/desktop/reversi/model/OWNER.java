package org.arthan.desktop.reversi.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Created by ashamsiev on 07.10.2015
 */
public enum OWNER {
    WHITE,
    BLACK,
    NONE;

    public Color getColor() {
        return this == WHITE ? Color.WHITE : Color.BLACK;
    }

    public String getColorStyle() {
        return this == WHITE ? "white" : "black";
    }

    public OWNER opposite() {
        return this == WHITE ? BLACK : this == BLACK ? WHITE : NONE;
    }
}
