package se.uog.appview;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import se.uog.controller.AppController;

public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; // Obvs can be changed later
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private AppMenu menuBar;

    public AppView(AppController appController) {
        super();

        this.menuBar = new AppMenu(appController);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setSize(600, 400);
        add(mainPanel);
    }


    public void addPage(JPanel page, int menuMneumonic) {
        mainPanel.add(page, page.getName());
        menuBar.addPage(page.getName(), menuMneumonic);
    }

    public void setPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public void toggleMenuBar(){menuBar.toggleEnabled();} // package-private

    // setPageEnabled()
    // Also need to set the menuBar to disabled here too for the page...

}
