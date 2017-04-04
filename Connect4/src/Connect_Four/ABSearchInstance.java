package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;

import java.util.*;

/**
 * Created by yungcheeze on 3/31/17.
 */
public class ABSearchInstance {
    private final Board initialBoard;
    private int winningN;
    private Set<Node> nodes;
    private Node root;
    private int maxNodes = 150000;
    private int playerNum;



    public ABSearchInstance(Board board, int winningN, int playerNum) {
       initialBoard = board;
       this.winningN = winningN;
       nodes = new HashSet<>();
       this.playerNum = playerNum;
       makeRoot();
    }

    private void makeRoot(){
        //create root node
        //assign to root
        //add to nodes
        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        int branchingFactor = initialBoard.getSizeH();
        List<Integer> moves = new ArrayList<>();
        int[] remainingMoves = getRemainingMovesArray();
        root = new MaxNode(alpha, beta, moves, remainingMoves, branchingFactor);
    }

    /**
     * Minimax search with alpha-beta pruning
     * Main Reference: http://web.cs.ucla.edu/~rosen/161/notes/alphabeta.html
     */
    public int alphaBetaSearch(){
        double val = Double.NEGATIVE_INFINITY;
        int depth = 0;
        int d = (int) Math.floor(Math.log(maxNodes) / Math.log(initialBoard.getSizeH()));
        int remainingMoves = 0;
        int[] remainingMovesArray = getRemainingMovesArray();
        for (int i = 0; i < remainingMovesArray.length; i++) {
            remainingMoves += remainingMovesArray[i];
        }
        int maxDepth = d <= remainingMoves ? d : remainingMoves;
        nodes.clear();
        nodes.add(root);
        Node currentNode = root;



        boolean done = false;
        while (!done) {
            if(depth == maxDepth) {
                val = evaluateNode(currentNode);
                currentNode.setLeafValue(val);
                if (currentNode.getParent().setAB(val)) currentNode.getParent().setSelectedChild(currentNode);
                currentNode = currentNode.getParent();
                depth--;
            } else if (currentNode.getAlpha() < currentNode.getBeta() && currentNode.childrenLeft()) {
                currentNode = currentNode.generateChild();
                nodes.add(currentNode);
                depth++;
            }else if(currentNode == root) {
                done = true;
            } else {
                if (!currentNode.setValue(val)) val = currentNode.getValue();
                if (currentNode.getParent().setAB(val)) currentNode.getParent().setSelectedChild(currentNode);
                currentNode = currentNode.getParent();
                depth--;
            }
        }

        Node moveChoice = root.getSelectedChild();
        return moveChoice.firstMove();
    }

    private double evaluateNode(Node node){
        Board currentBoard;
        currentBoard = initialBoard.clone();
        int i = 0;
        double heuristicValue = 0;
        for (int move : node.getMoves()) {
            try {
                i++;
                currentBoard.setPiece(move, (i + playerNum) % 2 + 1);
                Position position = currentBoard.getLastPosition();
                double positionValue = new AIBoardEvaluator().evaluatePosition(position, currentBoard, winningN);
                heuristicValue += position.getPieceNum() == playerNum ? positionValue : -positionValue;
                if (positionValue == Double.POSITIVE_INFINITY) break;
            } catch (BadPositionException e) {
                e.printStackTrace();
                continue;
            } catch (InvalidPieceException e) {
                e.printStackTrace();
                continue;
            } catch (ColumnFullException e) {
                e.printStackTrace();
                continue;
            }
        }
        return heuristicValue;
    }

    private int[] getRemainingMovesArray(){
        int[] remainingMoves = initialBoard.getNextPieceArray();
        for (int i = 0; i < remainingMoves.length; i++) {
            remainingMoves[i] = Math.abs(remainingMoves[i] - initialBoard.getSizeV());
        }
        return remainingMoves;
    }

}
