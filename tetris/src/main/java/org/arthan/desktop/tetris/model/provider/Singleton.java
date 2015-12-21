package org.arthan.desktop.tetris.model.provider;

/**
 * Created by Артур on 21.12.2015.
 * Next to Ufa.
 */
public class Singleton {

    static private Singleton instance = null;

    private Singleton() {

    }

    public static synchronized Singleton get() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
