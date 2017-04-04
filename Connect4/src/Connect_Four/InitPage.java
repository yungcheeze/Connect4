package Connect_Four;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Landing Page for GUI
 */
public class InitPage implements Page {
    private JButton singlePlayerVsAIButton;
    private JButton multiplayerPvPButton;
    private JPanel mainPanel;
    private GameEngine gameEngine;
    private PageContainer pageContainer;

    public InitPage (GameEngine gameEngine, PageContainer pageContainer) {
        this.gameEngine = gameEngine;
        this.pageContainer = pageContainer;

        singlePlayerVsAIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean ready = gameEngine.registerPlayer(PlayerType.HUMAN)
                        && gameEngine.initBoard(7, 6)
                        && gameEngine.registerPlayer(PlayerType.AI)
                        && gameEngine.startGame();
                if (ready) pageContainer.showPage("board");
                else System.out.println("Game Init Failure");
            }
        });
        multiplayerPvPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pageContainer.showPage("settings");
            }
        });
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
