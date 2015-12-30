package org.arthan.desktop.tetris.model.provider;

import com.google.common.collect.Lists;
import org.arthan.desktop.tetris.model.figure.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Created by ashamsiev on 17.12.2015
 */
public class InfiniteFigureProvider implements FigureProvider {

    private FigureOnScreen figure;
    private final Random random = new Random();

    @SuppressWarnings("unchecked")
    private final List<Class<? extends FigureOnScreen>> figureClasses = Lists.newArrayList(
            Square.class,
            Stick.class,
            LShape.class,
            JShape.class
    );

    public InfiniteFigureProvider(FigureOnScreen figure) {
        this.figure = figure;
    }

    public InfiniteFigureProvider() {
    }

    @Override
    public Optional<FigureOnScreen> next() {
        if (figure != null) {
            return Optional.of(figure.make(figure));
        } else {
            Class<? extends FigureOnScreen> figureClass = figureClasses.get(random.nextInt(figureClasses.size()));
            try {
                return Optional.of(figureClass.newInstance().onTop());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
