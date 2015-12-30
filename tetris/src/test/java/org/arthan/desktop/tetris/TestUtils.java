package org.arthan.desktop.tetris;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.arthan.desktop.tetris.controller.Game;
import org.arthan.desktop.tetris.model.GameScreen;
import org.arthan.desktop.tetris.model.figure.Pixel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

/**
 * Created by ashamsiev on 10.12.2015
 */
public class TestUtils {
    public static void assertPixelListEquals(String message, List<Pixel> expectedArray, List<Pixel> actualArray) {
        assertTrue(
                message +
                        "\nexpected: \n" + stringFromBlocks(expectedArray) +
                        "\nactual: \n" + stringFromBlocks(actualArray)
                ,
                expectedArray.containsAll(actualArray) && actualArray.containsAll(expectedArray));
    }

    private static String stringFromBlocks(List<Pixel> pixelList) {
        List<List<String>> rows = Lists.newArrayList();
        for (int i = 0; i < GameScreen.GAME_SCREEN_HEIGHT; i++) {
            ArrayList<String> row = Lists.newArrayList();
            for (int j = 0; j < GameScreen.GAME_SCREEN_WIDTH; j++) {
                row.add("__");
            }
            rows.add(row);
        }

        for (Pixel pixel : pixelList) {
            rows.get(pixel.y).set(pixel.x, "88");
        }

        return Joiner.on("\n").join(
                rows.stream()
                    .map(row -> Joiner.on("").join(row))
                    .collect(Collectors.toList())
        );
    }

    public static List<Pixel> readBlocksFromFile(String filePath) {
        String fileContent = readFile(filePath);
        List<Pixel> resultList = Lists.newArrayList();
        String[] rows = fileContent.split("\n");
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];
            String[] blocksInRow = row.split("(?<=\\G..)");
            for (int j = 0; j < blocksInRow.length; j++) {
                String block = blocksInRow[j];
                if (block.equals("88")) {
                    resultList.add(new Pixel(j, i));
                }
            }
        }
        return resultList;
    }

    public static String readFile(String path) {
        String expectedScreenArray;
        try {
            expectedScreenArray = FileUtils.readFileToString(new File(TestGui.class.getResource(path).toURI()));
            // code need to be cross-platform
            expectedScreenArray = expectedScreenArray.replace("\r", "");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return expectedScreenArray;
    }
}
