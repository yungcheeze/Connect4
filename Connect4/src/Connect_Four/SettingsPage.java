package Connect_Four;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI page used for custom game initialisation
 */
public class SettingsPage implements Page {
    private JComboBox numPlayers;
    private JComboBox winningCondition;
    private JComboBox rows;
    private JComboBox columns;
    private JButton proceedButton;
    private JPanel mainPanel;

    public SettingsPage (GameEngine gameEngine, PageContainer pageContainer) {
        numPlayers.setSelectedItem("2");
        winningCondition.setSelectedItem("4");
        columns.setSelectedItem("7");
        rows.setSelectedItem("6");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int np = Integer.valueOf(String.valueOf(numPlayers.getSelectedItem()));
                int wc =Integer.valueOf(String.valueOf(winningCondition.getSelectedItem()));
                int h =Integer.valueOf(String.valueOf(columns.getSelectedItem()));
                int v =Integer.valueOf(String.valueOf(rows.getSelectedItem()));
                boolean result = true;
                for (int i = 0; i < np; i++) {
                   result = result && gameEngine.registerPlayer(PlayerType.HUMAN);
                }
                gameEngine.setWinningN(wc);
                result = result && gameEngine.initBoard(h, v);
                result = result && gameEngine.startGame();
                if  (result) pageContainer.showPage("board");
                else System.out.println("Game Init Failure");
            }
        });
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
