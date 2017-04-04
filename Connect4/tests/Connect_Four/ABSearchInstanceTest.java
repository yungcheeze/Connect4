package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 4/2/17.
 */
public class ABSearchInstanceTest {
    Board board;
    ABSearchInstance searchInstance;
    @Before
    public void setUp() throws Exception {
       board = new Board(7,6);
       searchInstance = new ABSearchInstance(board, 4, 1);

    }

    @Test
    public void alphaBetaSearch() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board = new Board(2,4);
        board.setPiece(0, 1);
        board.setPiece(0, 1);
        board.setPiece(1, 2);
        board.setPiece(1, 2);
        new BoardPrinter().printBoard(board);
        searchInstance = new ABSearchInstance(board, 4, 1);
        int expected = 0;
        int actual = searchInstance.alphaBetaSearch();
        assertEquals(expected, actual);
    }

    @Test
    public void alphaBetaSearchWinningCase() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board = new Board(2,4);
        board.setPiece(0, 1);
        board.setPiece(0, 1);
        board.setPiece(0, 1);
        board.setPiece(1, 2);
        new BoardPrinter().printBoard(board);
        searchInstance = new ABSearchInstance(board, 4, 1);
        int expected = 0;
        int actual = searchInstance.alphaBetaSearch();
        assertEquals(expected, actual);
    }

    @Test
    public void alphaBetaSearchWinningCase2() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board = new Board(2,4);
        board.setPiece(1, 1);
        board.setPiece(1, 1);
        board.setPiece(1, 1);
        board.setPiece(0, 2);
        new BoardPrinter().printBoard(board);
        searchInstance = new ABSearchInstance(board, 4, 1);
        int expected = 1;
        int actual = searchInstance.alphaBetaSearch();
        assertEquals(expected, actual);
    }

    @Test
    public void alphaBetaSearchWinningCase3() throws Exception, InvalidPieceException, BadPositionException, ColumnFullException {
        board = new Board(4,4);
        board.setPiece(3, 1);
        board.setPiece(3, 1);
        board.setPiece(3, 1);
        board.setPiece(0, 2);
        new BoardPrinter().printBoard(board);
        searchInstance = new ABSearchInstance(board, 4, 1);
        int expected = 3;
        int actual = searchInstance.alphaBetaSearch();
        assertEquals(expected, actual);
    }

}