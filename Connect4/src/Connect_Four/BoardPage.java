package Connect_Four;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Main GUI page (where game is played)
 */
public class BoardPage implements Page {
    private JComboBox optionsList;
    private JButton makeMoveButton;
    private JTextPane boardTextPane;
    private JPanel mainPanel;
    private JLabel turnInfo;
    private JButton returnButton;
    private GameEngine gameEngine;
    private  PageContainer pageContainer;
    private BoardObserver boardObserver;

    public BoardPage(GameEngine gameEngine, PageContainer pageContainer) {
        this.gameEngine = gameEngine;
        this.pageContainer = pageContainer;
        boardObserver = new BoardObserver(boardTextPane);

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent componentEvent) {
                super.componentShown(componentEvent);
                setupPage();
                }
        });
        makeMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makeMove();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameEngine.resetGame();
                pageContainer.showPage("home");
            }
        });
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setupPage() {
        Board board = gameEngine.getBoard();
        int sizeH = board.getSizeH();
        String[] options = new String[sizeH];
        for (int i = 0; i < sizeH ; i++) {
           options[i] = String.valueOf(i);
        }
        optionsList.setModel(new DefaultComboBoxModel(options));
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        boardTextPane.setFont(font);
        gameEngine.registerBoardObserver(boardObserver);
        boardObserver.printBoard(board);
        turnInfo.setText(String.format("It's player %d's turn", gameEngine.nextPlayerNum()));
    }

    public void makeMove() {
        int move = optionsList.getSelectedIndex();
        boolean result = gameEngine.setNextMove(move);
        result = result && gameEngine.takeTurn();
        if(result) {
            turnInfo.setText(String.format("It's player %d's turn", gameEngine.nextPlayerNum()));
            if (gameEngine.nextPlayerType() == PlayerType.AI) {
                makeMove();
            }
        }
        else {
            turnInfo.setText("Something Went Wrong");
        }

        if (gameEngine.getState() == EngineState.GAMEOVER) {
            turnInfo.setText(String.format("Player %d Won!", gameEngine.winnerPlayerNum()));
        }
    }

}
