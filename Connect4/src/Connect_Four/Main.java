package Connect_Four;

import javax.swing.*;
import java.awt.*;

/**
 * Main method. Initialises GameEngine and JFrame
 */
public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(4);
        PageContainer pageContainer = new PageContainer();
        InitPage initPage = new InitPage(gameEngine, pageContainer);
        SettingsPage settingsPage = new SettingsPage(gameEngine, pageContainer);
        BoardPage boardPage = new BoardPage(gameEngine, pageContainer);
        pageContainer.addPage("home", initPage);
        pageContainer.addPage("settings", settingsPage);
        pageContainer.addPage("board", boardPage);
        JFrame frame = new JFrame("Connect4");
        frame.setContentPane(pageContainer.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
