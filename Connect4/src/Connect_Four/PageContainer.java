package Connect_Four;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * CardLayout manager used to switch between JPanels
 */
public class PageContainer {
    private JPanel mainPanel;
    private Map<String, Page> pages;
    private CardLayout cardLayout;

    public PageContainer(){
        pages = new HashMap<>();
        cardLayout = (CardLayout) mainPanel.getLayout();
    }

    boolean addPage(String reference, Page page) {
        pages.put(reference, page);
        mainPanel.add(page.getMainPanel(), reference);
        return true;
    }

    Page getPage(String reference){
        return pages.get(reference);
    }

    boolean showPage(String reference) {
        Page page = pages.get(reference);
        cardLayout.show(mainPanel, reference);
        return true;
    }

    JPanel getMainPanel(){
        return mainPanel;
    }
}
