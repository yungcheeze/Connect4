package Connect_Four;

import java.util.List;

/**
 * Interface used to define common functions between MaxNodes and MinNodes
 */
public interface Node {


    double getValue();

    boolean setValue(double value);

    double getAlpha();

    double getBeta();

    boolean setAB(double value);

    int firstMove();

    List<Integer> getMoves();

    Node getParent();

    void setParent(Node parent);

    boolean childrenLeft();

    Node generateChild();

    Node getSelectedChild();

    void setSelectedChild(Node child);

    NodeType getNodeType();

    void setLeafValue(double value);
}
