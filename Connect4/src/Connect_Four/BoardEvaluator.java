package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.NoPieceException;

import java.util.HashSet;
import java.util.Set;

/**
 * Class used by game engine to check if a winning move was made
 */
public class BoardEvaluator {

    public BoardEvaluator(){

    }

    public boolean evaluateBoardPosition(Position position,  Board board, int winningN){
        HashSet<Path> pathSet = new HashSet<Path>();
        int evalPiece;
        try {
            evalPiece = board.getPiece(position.getH(), position.getV());
            position.setPieceNum(evalPiece);
        } catch (NoPieceException e) {
            e.printStackTrace();
            return false;
        } catch (BadPositionException e) {
            e.printStackTrace();
            return false;
        }
        Path foundPath = verticalScan(position, board);
        if (foundPath.size() >= winningN) return true;
        addPath(foundPath, pathSet);
        foundPath = horizontalScan(position, board);
        if (foundPath.size() >= winningN) return true;
        addPath(foundPath, pathSet);
        foundPath = diagonalScanRight(position, board);
        if (foundPath.size() >= winningN) return true;
        addPath(foundPath, pathSet);
        foundPath = diagonalScanLeft(position, board);
        if (foundPath.size() >= winningN) return true;
        addPath(foundPath, pathSet);
        return false;
    }

    protected boolean addPath(Path path, Set<Path> pathSet){
       if (!pathSet.iterator().hasNext())
           return pathSet.add(path);
       Path inSet = pathSet.iterator().next();
       if (path.size() <= inSet.size())
           return false;
       else if (path.size() == inSet.size())
           return pathSet.add(path);
       else {
           pathSet.clear();
           return pathSet.add(path);
       }
    }

    private boolean extendPath(Position position, Path path, Board board) {
        try {
                int nextPiece = board.getPiece(position.getH(), position.getV());// method that triggers caught exceptions
                if (nextPiece == position.getPieceNum()) { //Stop condition; end of path reached
                    path.addPosition(position);
                    return true;
                }
        } catch (BadPositionException e) { //Stop condition; end of board reached
            //e.printStackTrace();
        } catch (NoPieceException e) { //Stop condition; end of path reached (i.e. empty position
            //e.printStackTrace();
        }
        return false;

    }

    private Position getNextPosition(Position current, Horizontal horizontal, Vertical  vertical){
        int hNext = current.getH() + horizontal.offset;
        int vNext = current.getV() + vertical.offset;
        return new Position(hNext, vNext, current.getPieceNum());
    }

    //TODO Make scans private
    protected Path verticalScan(Position position, Board board){
        Path foundPath = new Path();
        foundPath.addPosition(position);
        boolean extended = true;
        while(extended) {
            position = getNextPosition(position, Horizontal.STATIC, Vertical.DOWN);
            extended = extendPath(position, foundPath, board);
        }
        return foundPath;
    }

    protected Path horizontalScan(Position position, Board board) {
        int hStart = position.getH();
        int vStart = position.getV();
        Path foundPath = new Path();
        foundPath.addPosition(position);
        boolean extended = true;
        Horizontal horizontal = Horizontal.LEFT;
        Vertical vertical = Vertical.STATIC;

        while(extended) {
            position = getNextPosition(position, horizontal, vertical);
            extended = extendPath(position, foundPath, board);
            if (!extended && horizontal == Horizontal.LEFT){
                position.setH(hStart);
                position.setV(vStart);
                horizontal = Horizontal.RIGHT;
                extended = true;
            }
        }
        return foundPath;
    }

    protected Path diagonalScanLeft(Position position, Board board) {
        int hStart = position.getH();
        int vStart = position.getV();
        Path foundPath = new Path();
        foundPath.addPosition(position);
        boolean extended = true;
        Horizontal horizontal = Horizontal.LEFT;
        Vertical vertical = Vertical.DOWN;

        while(extended) {
            position = getNextPosition(position, horizontal, vertical);
            extended = extendPath(position, foundPath, board);
            if (!extended && vertical == Vertical.DOWN){
                position.setH(hStart);
                position.setV(vStart);
                vertical = Vertical.UP;
                horizontal = Horizontal.RIGHT;
                extended = true;
            }
        }
        return foundPath;
    }

    protected Path diagonalScanRight(Position position, Board board) {
        int hStart = position.getH();
        int vStart = position.getV();
        Path foundPath = new Path();
        foundPath.addPosition(position);
        boolean extended = true;
        Horizontal horizontal = Horizontal.RIGHT;
        Vertical vertical = Vertical.DOWN;

        while(extended) {
            position = getNextPosition(position, horizontal, vertical);
            extended = extendPath(position, foundPath, board);
            if (!extended && vertical == Vertical.DOWN){
                position.setH(hStart);
                position.setV(vStart);
                vertical = Vertical.UP;
                horizontal = Horizontal.LEFT;
                extended = true;
            }
        }
        return foundPath;
    }

}
