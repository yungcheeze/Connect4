package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 4/2/17.
 */
public class AIBoardEvaluatorTest {
    Board board;
    AIBoardEvaluator evaluator;
    @Before
    public void setUp() throws Exception {
        board = new Board(7,6);
        evaluator = new AIBoardEvaluator();

    }

    @Test
    public void evaluatePosition() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        Position position = new Position(0,0, 1);
        board.setPiece(0, 1);
        double expected = 4;
        double actual = evaluator.evaluatePosition(position, board, 4);
        assertEquals(expected, actual, 0.0001);

        position.setV(1);
        board.setPiece(0, 1);
        expected = 4 + 3;
        actual = evaluator.evaluatePosition(position, board, 4);
        assertEquals(expected, actual, 0.0001);

        position.setV(2);
        board.setPiece(0, 1);
        expected = 9 + 3;
        actual = evaluator.evaluatePosition(position, board, 4);
        assertEquals(expected, actual, 0.0001);

        position.setV(3);
        board.setPiece(0, 1);
        expected = 16 + 3;
        actual = evaluator.evaluatePosition(position, board, 4);
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void evaluatePositionComplex() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board.setPiece(0,1);
        board.setPiece(1,1);
        board.setPiece(1,1);
        board.setPiece(2,1);
        board.setPiece(2,1);
        board.setPiece(2,1);
        board.setPiece(3,1);
        board.setPiece(3,1);
        board.setPiece(4,1);
        board.setPiece(0,2);
        board.setPiece(1,2);
        board.setPiece(2,2);
        board.setPiece(3,2);
        board.setPiece(3,2);
        board.setPiece(4,2);
        board.setPiece(5,2);
        Position position = new Position(2,3, 2);
        double expected = 30;
        double actual = evaluator.evaluatePosition(position, board, 4);
        assertEquals(expected, actual, 0.00001);
    }
}