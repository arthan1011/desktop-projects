package org.arthan.desktop.tetris.model.provider;

import org.arthan.desktop.tetris.model.figure.FigureOnScreen;

import java.util.Optional;

/**
 * Created by ashamsiev on 17.12.2015
 */
public class InfiniteFigureProvider implements FigureProvider {

    private FigureOnScreen figure;

    public InfiniteFigureProvider(FigureOnScreen figure) {
        this.figure = figure;
    }

    @Override
    public Optional<FigureOnScreen> next() {
        return Optional.of(figure.make(figure));
    }
}
