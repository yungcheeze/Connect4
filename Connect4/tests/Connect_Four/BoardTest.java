package Connect_Four;

import Connect_Four.Exceptions.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 3/23/17.
 */
public class BoardTest {
    Board board;
    int sizeV;
    int sizeH;
    @org.junit.Before
    public void setUp() throws Exception {
        sizeH = 7;
        sizeV = 6;
        board = new Board(sizeH,sizeV);
    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void BoardIntiallyEmpty() throws Exception, NoPieceException, BadPositionException {
        for (int v = 0; v < sizeV; v++) {
            for (int h = 0; h < sizeH; h++){
                int actual = board.pieceAt(h, v);
                int expected = 0;
                assertEquals(expected, actual);
            }
        }
    }

    @Test (expected = BadPositionException.class)
    public void getPieceTooLow() throws Exception, NoPieceException, BadPositionException {
        board.getPiece(-1,-1);
    }

    @Test (expected = BadPositionException.class)
    public void getPieceTooHigh() throws Exception, NoPieceException, BadPositionException {
        board.getPiece(7,6);
    }

    @Test (expected = NoPieceException.class)
    public void getPieceEmptyPosition() throws Exception, NoPieceException, BadPositionException {
        board.getPiece(0,0);
    }

    @Test (expected = NoPieceException.class)
    public void getPieceEmptyPositionSafety() throws Exception, NoPieceException, BadPositionException {
        board.getPiece(6,5);
    }

    @Test
    public void setPiece() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        board.setPiece(0,1);
        int actual = board.getPiece(0,0);
        int expected = 1;
        assertEquals(expected, actual);
        board.setPiece(0, 2);
        actual = board.getPiece(0,1);
        expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    public void fillColumn() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        for (int i = 0; i < sizeV; i++) {
           board.setPiece(0, 1);
        }
        int actual = board.pieceAt(0, sizeV - 1);
        int expected = 1;
        assertEquals(expected, actual);
        board.setPiece(0, 1);
    }

    @Test (expected = ColumnFullException.class)
    public void overFillColumn() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        for (int i = 0; i <= sizeV; i++) {
            board.setPiece(0, 1);
        }
    }
    @Test
    public void boardCopyRightSize() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        int[][] boardCopy = board.getboardArray();
        for (int i = 0; i < boardCopy.length; i++) {
            assertArrayEquals(boardCopy[i], new int[sizeV]);
        }
    }

    @Test
    public void boardCopyUpdates() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        board.setPiece(0,1);
        board.setPiece(0,2);
        int[][] boardCopy = board.getboardArray();
        assertArrayEquals(boardCopy[0], new int[] {1,2,0,0,0,0});
    }

    @Test
    public void nextPieceArray() throws Exception, BadPositionException, NoPieceException, InvalidPieceException, ColumnFullException {
        board.setPiece(0,1);
        board.setPiece(0,2);
        int[] nextPieceArray = board.getNextPieceArray();
        assertArrayEquals(nextPieceArray, new int[] {2,0,0,0,0,0,0});
        board.setPiece(3,2);
        nextPieceArray = board.getNextPieceArray();
        assertArrayEquals(nextPieceArray, new int[] {2,0,0,1,0,0,0});
    }
}
