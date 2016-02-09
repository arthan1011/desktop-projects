package org.arthan.desktop.tetris.model.provider;

import org.arthan.desktop.tetris.model.FigureOnScreen;

import java.util.Optional;

/**
 * Created by ashamsiev on 15.12.2015
 */
public interface FigureProvider {
    Optional<FigureOnScreen> next();
}
