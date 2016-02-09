package org.arthan.desktop.tetris.model.provider;

import com.google.common.collect.Lists;
import org.arthan.desktop.tetris.model.FigureOnScreen;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Optional;

/**
 * Created by ashamsiev on 15.12.2015
 */
public class FigureListProvider implements FigureProvider {

    ListIterator<FigureOnScreen> figureListIterator;

    public FigureListProvider(FigureOnScreen... figureList) {
        ArrayList<FigureOnScreen> figures = Lists.newArrayList(figureList);
        this.figureListIterator = figures.listIterator();
    }

    @Override
    public Optional<FigureOnScreen> next() {
        if (figureListIterator.hasNext()) {
            return Optional.of(figureListIterator.next());
        } else {
            return Optional.empty();
        }
    }
}
