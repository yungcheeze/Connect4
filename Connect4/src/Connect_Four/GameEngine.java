package Connect_Four;

import Connect_Four.Exceptions.BadPositionException;
import Connect_Four.Exceptions.ColumnFullException;
import Connect_Four.Exceptions.InvalidPieceException;

import java.util.*;

/**
 * Class responsible for core logic behind game
 */
public class GameEngine {

    private List<Player> players;
    private Queue<Player> turnIterator;
    private int maxPlayers;
    private int winningN;
    private Player winner;
    private EngineState state;
    private Board board;
    private BoardEvaluator evaluator;

    public GameEngine (int maxPlayers){
        if (maxPlayers < 2 || maxPlayers > 6){
            this.maxPlayers = 2;
        }
        this.maxPlayers = maxPlayers;
        evaluator = new BoardEvaluator();
        turnIterator = new ArrayDeque<>();
        winningN = 4;
        players = new ArrayList<>();
        state = EngineState.INIT;
    }


    public boolean resetGame(){
        if (state == EngineState.INIT) return false;
        turnIterator = new ArrayDeque<>();
        players = new ArrayList<>();
        state = EngineState.INIT;
        board = null;
        return true;

    }


    public boolean registerPlayer(PlayerType playerType){
        Player player = playerType == PlayerType.HUMAN ? new HumanPlayer() : new AiPlayer(board, winningN);
        if (players.size() < maxPlayers && state == EngineState.INIT) {
            int playerNum = players.size() + 1;
            player.setPlayerNum(playerNum);
            return players.add(player);
        }
        else return false;
    }

    public boolean initBoard(int h, int v){
        if (state == EngineState.INIT) {
            board = new Board(h, v);
            return true;
        } else return false;
    }

    public boolean startGame() {
        if(board != null && players.size() >= 2 && state == EngineState.INIT) {
            state = EngineState.PLAYING;
            return true;
        } else return  false;
    }

    private Player nextPlayer(){
        if(turnIterator.peek() == null)
            turnIterator = new ArrayDeque<>(players);
        return turnIterator.peek();
    }

    public PlayerType nextPlayerType() {
        return nextPlayer().getPlayerType();
    }

    public int nextPlayerNum(){return nextPlayer().getPlayerNum();}

    public boolean takeTurn(){
        if(state != EngineState.PLAYING) return false;
        Player player = nextPlayer();
        if (!player.moveSet()) return false;
        else turnIterator.poll();
        int move = player.getMove();
        int playerNumber = player.getPlayerNum();
        try {
            board.setPiece(move, playerNumber);
            Position position = board.getLastPosition();
            boolean winningMove = evaluator.evaluateBoardPosition(position, board, winningN);
            if (winningMove) {
                winner = player;
                state = EngineState.GAMEOVER;
            }
            return true;
        } catch (BadPositionException e) {
            e.printStackTrace();
        } catch (InvalidPieceException e) {
            e.printStackTrace();
        } catch (ColumnFullException e) {
            e.printStackTrace();
        }
        return false;

    }

    public Board getBoard() {
            return board.clone();
    }

    public void registerBoardObserver(Observer observer) {
        board.addObserver(observer);
    }

    public EngineState getState() {
        return state;
    }

    public boolean setNextMove(int move) {
        if(nextPlayer().moveSet() || state != EngineState.PLAYING) return false;
        return nextPlayer().setMove(move);
    }

    public int winnerPlayerNum(){
        return winner.getPlayerNum();
    }

    public Player getWinner(){
        return winner;
    }

    public void setWinningN(int n){
        if(state == EngineState.INIT)
            winningN = n;
    }

    public int getWinningN(){return winningN;}

    public void setMaxPlayers(int maxPlayers) {
        if(state == EngineState.INIT)
            this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers(){return maxPlayers;}
    //TODO throw exception if invalid piece number provided

    /*
    *Init game:
    * add players
    * Play game:
    * -get move from player
    * -make move on board
    * -evaluate position to see if winner exists*/
}
