package Connect_Four;

import java.util.Objects;

/**
 * Object used to represent a postion on the board. Needed by Path class
 */
public class Position {
    private int v;
    private int h;
    private int pieceNum;
    public Position(int h, int v){
        setH(h);
        setV(v);
        pieceNum = 0;
    }
    public Position(int h, int v, int pieceNum){
        setH(h);
        setV(v);
        this.pieceNum = pieceNum;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getPieceNum() {
        return pieceNum;
    }

    public void setPieceNum(int pieceNum) {
        this.pieceNum = pieceNum;
    }

    public Position clone(){
        return new Position(v, h, pieceNum);
    }

    @Override
    public boolean equals(Object o) {
        Position p;
        try {
            p = (Position) o;
        } catch (ClassCastException e){
            return false;
        }

        if (p.getH() == h && p.getV() == v) return true;
        else return false;
    }

    @Override
    public String toString() {
       return String.format("( %d, %d)", h, v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, v);
    }
}
