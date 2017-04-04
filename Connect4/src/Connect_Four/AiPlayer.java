package Connect_Four;

/**
 * AI implementation of player interface
 */
public class AiPlayer implements Player {

    PlayerType playerType;
    int move;
    int playerNum;
    Board board;
    int winningN;
    boolean moveSet;

    public AiPlayer(Board board, int winningN) {
        playerType = PlayerType.AI;
        this.board = board;
        this.winningN = winningN;
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
        this.move = new ABSearchInstance(board, winningN, playerNum).alphaBetaSearch();
        moveSet = true;
        return true;
    }

    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

}
