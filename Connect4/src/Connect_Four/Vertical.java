package Connect_Four;

/**
 * Enum used to provide vertical offset when scanning a position using the BoardEvaluator
 */
public enum Vertical {
    STATIC(0),
    UP (1),
    DOWN (-1);
    int offset;



    Vertical(int offset) {
        this.offset = offset;
    }
}
