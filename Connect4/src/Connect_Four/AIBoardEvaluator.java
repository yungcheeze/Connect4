package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.NoPieceException;

import java.util.HashSet;

/**
 * Extension of boardEvaluator, Evaluations return heuristic value of piece instead of boolean
 */
public class AIBoardEvaluator extends BoardEvaluator {

    public AIBoardEvaluator() {
        super();
    }

    public double evaluatePosition(Position position, Board board, int winningN){
        HashSet<Path> pathSet = new HashSet<Path>();
        int evalPiece;
        try {
            evalPiece = board.getPiece(position.getH(), position.getV());
        } catch (NoPieceException e) {
            e.printStackTrace();
            return 0;
        } catch (BadPositionException e) {
            e.printStackTrace();
            return 0;
        }
        position.setPieceNum(evalPiece);
        double heuristicValue = 0;
        Path foundPath = verticalScan(position, board);
        heuristicValue += foundPath.size() < winningN ? foundPath.size()*foundPath.size() : Double.POSITIVE_INFINITY;
        foundPath = horizontalScan(position, board);
        heuristicValue += foundPath.size() < winningN ? foundPath.size()*foundPath.size() : Double.POSITIVE_INFINITY;
        foundPath = diagonalScanRight(position, board);
        heuristicValue += foundPath.size() < winningN ? foundPath.size()*foundPath.size() : Double.POSITIVE_INFINITY;
        foundPath = diagonalScanLeft(position, board);
        heuristicValue += foundPath.size() < winningN ? foundPath.size()*foundPath.size() : Double.POSITIVE_INFINITY;
        return heuristicValue;
    }
}
