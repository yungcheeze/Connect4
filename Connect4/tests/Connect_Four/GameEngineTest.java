package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 3/29/17.
 */
public class GameEngineTest {
    GameEngine gameEngine;

    @Before
    public void setUp() throws Exception {
        gameEngine = new GameEngine(2);

    }

    @Test
    public void registerPlayer() throws Exception {
        gameEngine.registerPlayer(PlayerType.HUMAN);
        PlayerType actual = gameEngine.nextPlayerType();
        assertEquals(PlayerType.HUMAN, actual);
        gameEngine = new GameEngine(2);
        gameEngine.registerPlayer(PlayerType.AI);
        actual = gameEngine.nextPlayerType();
        assertEquals(PlayerType.AI, actual);
    }

    @Test
    public void initBoard() throws Exception {
        gameEngine.initBoard(6,4);
        Board board = gameEngine.getBoard();
        assertEquals(6, board.getSizeH());
        assertEquals(4, board.getSizeV());

    }

    @Test
    public void startGame() throws Exception {
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.initBoard(7,6);
        gameEngine.startGame();
        assertEquals(EngineState.PLAYING, gameEngine.getState());
    }

    @Test
    public void takeTurn() throws Exception, BadPositionException {
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.initBoard(7,6);
        gameEngine.startGame();
        gameEngine.setNextMove(1);
        gameEngine.takeTurn();
        int actual = gameEngine.getBoard().pieceAt(1,0);
        assertEquals(1, actual);

    }

    @Test
    public void winnerPlayerNum() throws Exception {
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.registerPlayer(PlayerType.HUMAN);
        gameEngine.initBoard(7,6);
        gameEngine.startGame();
        gameEngine.setNextMove(1);
        gameEngine.takeTurn();
        gameEngine.setNextMove(2);
        gameEngine.takeTurn();
        gameEngine.setNextMove(1);
        gameEngine.takeTurn();
        gameEngine.setNextMove(2);
        gameEngine.takeTurn();
        gameEngine.setNextMove(3);
        gameEngine.takeTurn();
        gameEngine.setNextMove(2);
        gameEngine.takeTurn();
        gameEngine.setNextMove(3);
        gameEngine.takeTurn();
        gameEngine.setNextMove(2);
        gameEngine.takeTurn();

        assertEquals(EngineState.GAMEOVER, gameEngine.getState());
        assertEquals(2, gameEngine.winnerPlayerNum());
        assertEquals(false, gameEngine.takeTurn());
    }

}