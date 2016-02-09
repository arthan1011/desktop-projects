package org.arthan.desktop.tetris;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by ashamsiev on 10.12.2015
 */
public class TestUtils {
    public static void assertListEquals(String message, List<?> expectedArray, List<?> actualArray) {
        assertTrue(
                message +
                        "\nexpected: " + expectedArray +
                        "\nactual: " + actualArray
                ,
                expectedArray.containsAll(actualArray) && actualArray.containsAll(expectedArray));
    }
}
