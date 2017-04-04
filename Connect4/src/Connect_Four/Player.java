package Connect_Four;

/**
 * Interface used to implement common Human and Ai Player methods
 */
public interface Player {

    int getPlayerNum();

    boolean setPlayerNum(int playerNum);

    int getMove();

    boolean moveSet();

    boolean setMove(int move);

    PlayerType getPlayerType();
}
