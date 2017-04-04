package Connect_Four;

/**
 * Class used to represent a human player in the GameEngine
 */
public class HumanPlayer implements Player {
    int move;
    PlayerType playerType;
    int playerNum;
    boolean moveSet;

    public HumanPlayer(){
        playerType = PlayerType.HUMAN;
        moveSet = false;
    }

    @Override
    public int getPlayerNum() {
        return playerNum;
    }

    @Override
    public boolean setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
        return true;
    }

    @Override
    public int getMove() {
        moveSet = false;
        return move;
    }

    @Override
    public boolean moveSet() {
        return moveSet;
    }

    @Override
    public boolean setMove(int move) {
        if(moveSet) return false;
        this.move = move;
        moveSet = true;
        return true;
    }

    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

}
