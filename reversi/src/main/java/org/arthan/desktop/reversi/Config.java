package org.arthan.desktop.reversi;

import org.arthan.desktop.reversi.model.OWNER;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Arthur Shamsiev on 18.10.15.
 * Using IntelliJ IDEA
 * Project - desktop
 */
public class Config {

    static Properties properties = new Properties();

    static {
        InputStream in = Config.class.getResourceAsStream("/config.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static OWNER getPlayerColor() {
        return OWNER.valueOf(properties.getProperty("color"));
    }

    public static String getRestURL() {
        return properties.getProperty("rest.url");
    }
}
