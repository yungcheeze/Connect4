package Connect_Four;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Class that updates the Board GUI every time the board changes
 */
public class BoardObserver implements Observer {
    JTextPane boardTextPane;

    public BoardObserver(JTextPane boardTextPane){
        this.boardTextPane = boardTextPane;
    }
    @Override
    public void update(Observable observable, Object o) {
        Board board = (Board) o;
        printBoard(board);
    }

    public void printBoard(Board board) {
        String boardString = new BoardPrinter().getBoardString(board);
        boardTextPane.setText(boardString);
    }
}
