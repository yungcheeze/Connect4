package Connect_Four;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yungcheeze on 4/1/17.
 */
public class NodeTest {

    Node maxNode;
    Node minNode;
    double a0;
    double b0;

    @Before
    public void setUp() throws Exception {
        a0 = Double.NEGATIVE_INFINITY;
        b0 = Double.POSITIVE_INFINITY;
        List<Integer> moves = new ArrayList<>();
        int maxChildren = 5;
        int[] remainingMoves = new int[] {1,1,1,1,1};
        maxNode = new MaxNode(a0, b0, moves, remainingMoves, 5);
        minNode = new MinNode(a0, b0, moves, remainingMoves, 5);
    }

    @Test
    public void setValMax() throws Exception {
        boolean expected = true;
        boolean actual = maxNode.setValue(1000);
        assertEquals(expected, actual);
        expected = true;
        actual = maxNode.setValue(1001);
        assertEquals(expected, actual);
        expected = false;
        actual = maxNode.setValue(1000);
        assertEquals(expected, actual);

    }

    @Test
    public void setValMin() throws Exception {
        boolean expected = true;
        boolean actual = minNode.setValue(-1000);
        assertEquals(expected, actual);
        expected = true;
        actual = minNode.setValue(-1001);
        assertEquals(expected, actual);
        expected = false;
        actual = minNode.setValue(-1000);
        assertEquals(expected, actual);
    }

    @Test
    public void parentTest() throws Exception {
        maxNode.setParent(minNode);
        assertEquals(minNode, maxNode.getParent());
    }

    @Test
    public void setABMax() throws Exception {
        boolean expected = true;
        boolean actual = maxNode.setAB(1000);
        assertEquals(expected, actual);
        expected = true;
        actual = maxNode.setAB(1001);
        assertEquals(expected, actual);
        expected = false;
        actual = maxNode.setAB(1000);
        assertEquals(expected, actual);
    }

    @Test
    public void setABMin() throws Exception {
        boolean expected = true;
        boolean actual = minNode.setAB(-1000);
        assertEquals(expected, actual);
        expected = true;
        actual = minNode.setAB(-1001);
        assertEquals(expected, actual);
        expected = false;
        actual = minNode.setAB(-1000);
        assertEquals(expected, actual);
    }

    @Test
    public void generateChildMax() throws Exception {
        assertEquals(true, minNode.childrenLeft());
        Node child;
        List<Integer> moves = new ArrayList<>();
        moves.add(0);
        for (int i = 0; i < 5; i++) {
            assertEquals(true, minNode.childrenLeft());
            child = maxNode.generateChild();
            assertEquals(NodeType.MIN, child.getNodeType());
            assertEquals(maxNode, child.getParent());
            moves.set(0, i);
            assertEquals(moves, child.getMoves());
        }
        assertEquals(false, minNode.childrenLeft());
    }

    @Test
    public void generateChildMin() throws Exception {
        assertEquals(true, minNode.childrenLeft());
        Node child;
        List<Integer> moves = new ArrayList<>();
        moves.add(0);
        for (int i = 0; i < 5; i++) {
            assertEquals(true, minNode.childrenLeft());
            child = minNode.generateChild();
            assertEquals(NodeType.MAX, child.getNodeType());
            assertEquals(minNode, child.getParent());
            moves.set(0, i);
            assertEquals(moves, child.getMoves());
        }
        assertEquals(false, minNode.childrenLeft());
    }

    @Test
    public void selectedChildTest() throws Exception {
        Node child = maxNode.generateChild();
        maxNode.setSelectedChild(child);
        assertEquals(child, maxNode.getSelectedChild());
        child = minNode.generateChild();
        minNode.setSelectedChild(child);
        assertEquals(child, minNode.getSelectedChild());
    }

    @Test
    public void firstMove() throws Exception {

    }

    @Test
    public void getMoves() throws Exception {

    }

    @Test
    public void nodeType() throws Exception {
        assertEquals(NodeType.MAX, maxNode.getNodeType());
        assertEquals(NodeType.MIN, minNode.getNodeType());
    }

}