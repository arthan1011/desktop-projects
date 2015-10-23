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
    private static final String GAME_STATE_AFTER_FIRST_PLAY = "00000000000000000000000000021000000110000000100000000000000000002";
    private static final String GAME_STATE_AFTER_SECOND_PLAY =
                    "00000000" +
                    "00000000" +
                    "00000000" +
                    "00022200" +
                    "00011000" +
                    "00001000" +
                    "00000000" +
                    "000000001";

    private ReversiClient firstReversiClient;
    private ReversiServer reversiServer;
    private ReversiClient secondReversiClient;

    @Test
    public void testSingleClientConnection() throws Exception {
        startServer(); // starts server with initial game state
        startAndConnectFirstClient(); // creates client object and connect it to server
        GameInfo retrievedGameInfo = requestGameInfoFromServer(); // make client do request for game info
        checkClientReceivedInitialGameState(retrievedGameInfo);
    }

    @Test
    public void testSingleClientPlay() throws Exception {
        startServer();
        startAndConnectFirstClient();
        GameInfo gameInfo = playAndRetrieveInfo(firstReversiClient, 5, 4);
        checkGameStateAfterClientPlay(gameInfo, GAME_STATE_AFTER_FIRST_PLAY);
    }

    @Test
    public void testTwoClientsPlayTwoTurns() throws Exception {
        startServer();
        startAndConnectFirstClient();
        startAndConnectSecondClient();
        GameInfo gameInfo = playAndRetrieveInfo(firstReversiClient, 5, 4);
        checkGameStateAfterClientPlay(gameInfo, GAME_STATE_AFTER_FIRST_PLAY);
        gameInfo = playAndRetrieveInfo(secondReversiClient, 3, 5);
        checkGameStateAfterClientPlay(gameInfo, GAME_STATE_AFTER_SECOND_PLAY);
    }

    @Test
    public void secondClientShouldReceiveInfoAfterFirstClientPlay() throws Exception {
        startServer();
        startAndConnectFirstClient();
        startAndConnectSecondClient();
        GameInfo gameInfo = playAndRetrieveInfo(firstReversiClient, 5, 4);
        GameInfo gameInfoRetrievedBySecondClient = askForGameInfo(secondReversiClient);
        checkSecondClientReceivedUpdatedInfo(gameInfo, gameInfoRetrievedBySecondClient);
    }

    private void checkSecondClientReceivedUpdatedInfo(GameInfo gameInfo, GameInfo gameInfoRetrievedBySecondClient) {
        Assert.assertEquals(
                "Second player should have received updated board state after first player turn",
                gameInfo,
                gameInfoRetrievedBySecondClient
        );
    }

    private GameInfo askForGameInfo(ReversiClient reversiClient) {
        return reversiClient.retriveInfo();
    }

    private void startAndConnectSecondClient() {
        secondReversiClient = new ReversiClient(TEST_SERVER_HOST, TEST_SERVER_PORT);
    }

    private void checkGameStateAfterClientPlay(GameInfo gameInfo, String gameStateAfterFirstPlay) {
        Assert.assertEquals(
                "Expected board state after first player turn",
                new GameInfo(gameStateAfterFirstPlay),
                gameInfo
        );
    }

    private GameInfo playAndRetrieveInfo(ReversiClient reversiClient, int x, int y) {
        return reversiClient.play(x, y);
    }

    private void startServer() {
        reversiServer = new ReversiServer(TEST_SERVER_PORT);
    }

    private void startAndConnectFirstClient() {
        firstReversiClient = new ReversiClient(TEST_SERVER_HOST, TEST_SERVER_PORT);
    }

    private GameInfo requestGameInfoFromServer() {
        return firstReversiClient.retriveInfo();
    }

    private void checkClientReceivedInitialGameState(GameInfo retrievedGameInfo) {
        Assert.assertEquals(
                "Expected initial board state",
                new GameInfo(INITIAL_GAME_STATE_INFO_STRING),
                retrievedGameInfo);
    }

    @After
    public void tearDown() throws Exception {
        reversiServer.reset();
        reversiServer.close();

    }
}
