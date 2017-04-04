package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 3/27/17.
 */
public class BoardEvaluatorTest {
    int sizeH, sizeV;
    Board board;
    BoardEvaluator evaluator;
    @Before
    public void setUp() throws Exception {
        sizeH = 7;
        sizeV = 6;
        board = new Board(sizeH, sizeV);
        evaluator = new BoardEvaluator();

    }

    @Test
    public void evaluateBoardPosition() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board.setPiece(0,1);
        board.setPiece(1,2);
        board.setPiece(1,2);
        board.setPiece(1,2);
        board.setPiece(1,2);
        board.setPiece(2,1);
        board.setPiece(3,2);
        board.setPiece(3,1);
        board.setPiece(4, 1);
        new BoardPrinter().printBoard(board);
        Position eval = new Position(1,3,2);
        boolean actual = evaluator.evaluateBoardPosition(eval, board, 4);
        boolean expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void evaluateBoardPositionDiag() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board.setPiece(0,1);
        board.setPiece(1,2);
        board.setPiece(1,2);
        board.setPiece(1,2);
        board.setPiece(1,1);
        board.setPiece(2,1);
        board.setPiece(2,2);
        board.setPiece(2,1);
        board.setPiece(3,2);
        board.setPiece(3,1);
        board.setPiece(4,1);
        new BoardPrinter().printBoard(board);
        Position eval = new Position(1,3,1);
        boolean actual = evaluator.evaluateBoardPosition(eval, board, 4);
        boolean expected = true;
        assertEquals(expected, actual);
        eval.setV(2);
        eval.setPieceNum(2);
        actual = evaluator.evaluateBoardPosition(eval, board, 3);
        expected = true;
        assertEquals(expected, actual);
    }

}