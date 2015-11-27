package org.arthan.desktop.tetris;

import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.arthan.desktop.tetris.util.UIBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.loadui.testfx.Assertions;
import org.loadui.testfx.GuiTest;

/**
 * Created by Arthur Shamsiev on 27.11.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class SimpleTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent;
        parent = UIBuilder.createRoot();

        return parent;
    }

    @Test
    public void testGridScreen() throws Exception {

        GridPane screen = find("#screen");

        Rectangle cell = (Rectangle) screen.getChildren().get(0);

        Assert.assertEquals(cell.getFill(), Color.LIGHTGREY);

        Assertions.verifyThat(screen, (GridPane gp) -> gp.getHgap() == 4);

        Assert.assertThat((int)screen.getHgap(), Matchers.equalTo(4));
    }
}
