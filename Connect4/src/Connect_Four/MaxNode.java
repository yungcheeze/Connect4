package Connect_Four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class used to represent maximiser node in minimax search-tree
 */
public class MaxNode implements Node {

    private double value;
    private double alpha;
    private double beta;
    private List<Integer> moves;
    private Node parent;
    private Node selectedChild;
    private int branchingFactor;
    private int [] remainingMoves;
    private ChildSlot[] childSlots;
    private NodeType nodeType;

    public MaxNode(double alpha, double beta, List<Integer> moves, int[]remainingMoves, int branchingFactor) {
        this.branchingFactor = branchingFactor;
        this.remainingMoves = remainingMoves;
        initChildSlots(remainingMoves);
        this.alpha = alpha;
        this.beta = beta;
        this.moves = moves;
        nodeType = NodeType.MAX;
        value = Double.NEGATIVE_INFINITY;
    }

    private void initChildSlots( int[] remainingMoves){
        childSlots = new ChildSlot[remainingMoves.length];
        for (int i = 0; i < remainingMoves.length; i++) {
            childSlots[i] = remainingMoves[i] > 0 ? ChildSlot.FREE : ChildSlot.NULL;
        }
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public boolean setValue(double value) {
        double initialValue = value;
        this.value = Math.max(value, alpha);
        return initialValue != value;
    }

    @Override
    public double getAlpha() {
        return alpha;
    }

    @Override
    public double getBeta() {
        return beta;
    }

    @Override
    public boolean setAB(double value) {
        if (value > alpha) {
            alpha = value;
            return true;
        }
        return false;
    }

    @Override
    public int firstMove() {
        return moves.get(0);
    }

    @Override
    public List<Integer> getMoves() {
        return moves;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public boolean childrenLeft() {
        return nextFreeChildSlot() < branchingFactor;
    }

    private int nextFreeChildSlot() {
        for (int i = 0; i < childSlots.length; i++) {
            if (childSlots[i] == ChildSlot.FREE) return i;
        }
        return childSlots.length + 1;
    }

    @Override
    public Node generateChild() {
        int nextMove = nextFreeChildSlot();
        if (nextMove >= branchingFactor)
            return null;
        else {
            ArrayList<Integer> childMoves = new ArrayList<>(moves);
            childMoves.add(nextMove);
            childSlots[nextMove] = ChildSlot.CHILD;
            int[] childRemainingMoves = Arrays.copyOf(remainingMoves, remainingMoves.length);
            childRemainingMoves[nextMove]--;
            Node childNode = new MinNode(alpha, beta, childMoves, childRemainingMoves, branchingFactor);
            childNode.setParent(this);
            return childNode;
        }
    }

    @Override
    public Node getSelectedChild() {
        return selectedChild;
    }

    @Override
    public void setSelectedChild(Node child) {
        this.selectedChild = child;
    }

    @Override
    public NodeType getNodeType() {
        return nodeType;
    }

    @Override
    public void setLeafValue(double value) {
        this.value = value;
    }
}
