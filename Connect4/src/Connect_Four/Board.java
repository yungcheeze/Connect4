package Connect_Four;

import Connect_Four.Exceptions.*;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 * Class used to represent game board
 */
public class Board extends Observable{
    private int[][] boardArray; //board[h][v] h goes right, v goes up
    private int sizeV; // number of vertical tiles
    private int sizeH; //number of horizontal tiles
    private int[] nextPiece; //nextPiece[h] gets valid v value for setting board[h][v]
    private Position lastPosition;
    //when placing piece i use received h value and v=nextPiece[h]

    public Board(int sizeH, int sizeV){
        if(sizeH < 1 || sizeH > 20){
            //TODO raise sizing exception
            sizeH=7;
        }
        if(sizeV < 1 || sizeV > 20){
            //TODO raise sizing exception
            sizeV=6;
        }

        this.sizeH=sizeH;
        this.sizeV=sizeV;

        boardArray= new int[sizeH][sizeV];
        nextPiece = new int[sizeH];
    }

    private Board(int [][] boardArray, int[] nextPiece, Position lastPosition) {
        sizeH = boardArray.length;
        sizeV = boardArray[0].length;
        this.boardArray = boardArray;
        this.nextPiece = nextPiece;
        this.lastPosition = lastPosition;
    }
    /**
     * Return piece on board
     * @param v position on vertical axis (going upwards) (range: 0 to vMax - 1)
     * @param h position on horizontal axis (going left to right)(range: 0 to hMax - 1)
     * @return int player number (0 = empty position,  n = player 'n' piece)
     * @throws BadPositionException if v or h are invalid co-ordinates;
     * @throws NoPieceException if given position has no piece on it
     * */
    public int getPiece(int h, int v) throws NoPieceException, BadPositionException {
        if (!isValidPosition(h, v)) throw new BadPositionException();
        int piece = boardArray[h][v];
        if (piece == 0){
            throw new NoPieceException();
        }
        return piece;
    }

    /**
     * Return piece on board (less strict version of getPiece() i.e. returns empty slots as well).
     * @param v position on vertical axis (going upwards) (range: 0 to vMax - 1).
     * @param h position on horizontal axis (going left to right)(range: 0 to hMax - 1).
     * @return int player number (0 = empty position,  n = player 'n' piece).
     * @throws BadPositionException if v or h are invalid co-ordinates.
     * */
    public int pieceAt(int h, int v) throws BadPositionException {
        if (!isValidPosition(h, v)) throw new BadPositionException();
        int piece = boardArray[h][v];
        return piece;
    }

    /**
     * Place piece in row h with value num
     * @param h column to place piece on.
     * @param num integer value to place at column h.
     * @throws BadPositionException if v or h are invalid co-ordinates.
     * @throws InvalidPieceException if 0 or negative num is provided;
     * @throws ColumnFullException if no more pieces can be placed at column H;
     * */
    public void setPiece(int h, int num) throws BadPositionException, InvalidPieceException, ColumnFullException {
        if (!isValidPosition(h, 0)) throw new BadPositionException();
        if (num <= 0) throw new InvalidPieceException();
        if(nextPiece[h] == sizeV) throw new ColumnFullException();
        int v = nextPiece[h];
        boardArray[h][v] = num;
        setLastPosition(h, v, num);
        nextPiece[h] += 1;
        setChanged();
        notifyObservers(clone());
    }

    /**
     * Returns deep copy of current board array
     * @return 2D int array representing board
     */

    public int[][] getboardArray(){
        int[][] boardCopy = new int[sizeH][sizeV];
        for (int h = 0; h < sizeH; h++) {
            boardCopy[h] = Arrays.copyOf(boardArray[h], boardArray[h].length);
        }
        return  boardCopy;
    }

    /**
     * Returns array that shows number of pieces placed in each column
     * @return integer array
     */
    public int[] getNextPieceArray(){
        int[] nextPieceCopy = Arrays.copyOf(nextPiece, nextPiece.length);
        return nextPieceCopy;
    }

    private boolean isValidPosition(int h, int v) {
        int hDiff = sizeH - h;
        int vDiff = sizeV - v;
        if(vDiff <= 0 || hDiff <= 0){
            return false;
        }
        else if(vDiff > sizeV || hDiff > sizeH){
            return false;
        }

        return true;

    }

    private void setLastPosition(int h, int v, int pieceNum) {
       lastPosition = new Position(h, v, pieceNum);
    }

    /**
     * Returns position where last piece was placed
     * @return Position object
     */
    public Position getLastPosition(){
        if  (lastPosition == null) throw new NullPointerException();
        return lastPosition;
    }

    /**
     * Returns number of rows on board
     * @return single integer
     */
    public int getSizeV(){
        return sizeV;
    }

    /**
     * Returns number of columns on board
     * @return single integer
     */
    public int getSizeH(){
        return sizeH;
    }

    /**
     * Returns a deep copy of the entire board
     * @return Board Object
     */
    public Board clone() {
        Position position = lastPosition == null ? new Position(0,0,1) : lastPosition.clone();
        return  new Board(getboardArray(), getNextPieceArray(), position);
    }
}
