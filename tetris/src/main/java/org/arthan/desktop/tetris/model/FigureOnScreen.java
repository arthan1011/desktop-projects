package org.arthan.desktop.tetris.model;

import java.util.Arrays;

/**
 * Created by ashamsiev on 09.12.2015
 */
public class FigureOnScreen {

    public static final Pixel[] SQUARE_ON_TOP = new Pixel[]{
            new Pixel(4, 0),
            new Pixel(5, 0),
            new Pixel(4, 1),
            new Pixel(5, 1)
    };
    public static final Pixel[] TEST_SQUARE_NEAR_BOTTOM = new Pixel[]{
            new Pixel(4, 16),
            new Pixel(5, 16),
            new Pixel(4, 17),
            new Pixel(5, 17)
    };

    private Pixel[] pixels;

    public FigureOnScreen(Pixel[] pixels) {
        this.pixels = Arrays.copyOf(pixels, pixels.length);
    }

    public Pixel[] getPixels() {
        return Arrays.copyOf(pixels, pixels.length);
    }

    public FigureOnScreen goDown() {

        FigureOnScreen downFigure = new FigureOnScreen(pixels);
        Pixel[] pixels = downFigure.pixels;

        for (int i = 0; i < pixels.length; i++) {
            Pixel oldPixel = pixels[i];
            pixels[i] = new Pixel(oldPixel.x, oldPixel.y + 1);
        }

        return downFigure;
    }

    public boolean isInTheBottom() {
        return findLowestY() == GameScreen.GAME_SCREEN_HEIGHT - 1;
    }

    int findLowestY() {
        int result = 0;
        for (int i = 0; i < pixels.length; i++) {
            Pixel pixel = pixels[i];
            result = Math.max(pixel.y, result);
        }
        return result;
    }
}
