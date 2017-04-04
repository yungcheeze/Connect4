package Connect_Four;

/**
 * Enum used to provide horizontal offset when scanning a position using the BoardEvaluator
 */
public enum Horizontal {
    STATIC(0),
    LEFT (-1),
    RIGHT (1);
    int offset;

    Horizontal(int offset) {
        this.offset = offset;
    }
}
