package org.arthan.desktop.reversi.server;

import org.arthan.desktop.reversi.model.GameInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ashamsiev on 21.10.2015
 */
public class CommunicationTest {

    private static final int TEST_SERVER_PORT = 48888;
    private static final String TEST_SERVER_HOST = "localhost";
    private static final String INITIAL_GAME_STATE_INFO_STRING = "00000000000000000000000000021000000120000000000000000000000000001";
    private static final String GAME_STATE_AFTER_FIRST_PLAY = "00000000000000000000000000021000000111000000000000000000000000002";

    private ReversiClient reversiClient;
    private ReversiServer reversiServer;

    @Test
    public void testSingleClientConnection() throws Exception {
        startServer(); // starts server with initial game state
        startAndConnectClient(); // creates client object and connect it to server
        GameInfo retrievedGameInfo = requestGameInfoFromServer(); // make client do request for game info
        checkClientReceivedInitialGameState(retrievedGameInfo);
    }

    @Test
    public void testSingleClientPlay() throws Exception {
        startServer();
        startAndConnectClient();
        GameInfo gameInfo = playAndRetrieveInfo();
        checkClientReceivedGameStateAfterPlay(gameInfo);
    }

    private GameInfo playAndRetrieveInfo() {
        return reversiClient.play(5, 4);
    }

    private void startServer() {
        reversiServer = new ReversiServer(TEST_SERVER_PORT);
    }

    private void startAndConnectClient() {
        reversiClient = new ReversiClient(TEST_SERVER_HOST, TEST_SERVER_PORT);
    }

    private GameInfo requestGameInfoFromServer() {
        return reversiClient.retriveInfo();
    }

    private void checkClientReceivedInitialGameState(GameInfo retrievedGameInfo) {
        Assert.assertEquals(
                "Expected initial board state",
                new GameInfo(INITIAL_GAME_STATE_INFO_STRING),
                retrievedGameInfo);
    }

    private void checkClientReceivedGameStateAfterPlay(GameInfo gameInfo) {
        Assert.assertEquals(
                "Expected board state after first player turn",
                new GameInfo(GAME_STATE_AFTER_FIRST_PLAY),
                gameInfo
        );
    }
}
