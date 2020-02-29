package se.uog.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; // Obvs can be changed later
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private AppMenu menuBar;

    public AppView(AppController appController) {
        super();

        this.menuBar = new AppMenu(appController);
        setJMenuBar(menuBar);

        setTitle(WINDOW_TITLE);
        setSize(600, 400);
        add(mainPanel);

        // Handle closing
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Do nothing, as the listener will handle this.
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                appController.close();
            }
        });
    }

    public void addPage(JPanel page, String pageName, int menuMneumonic) {
        mainPanel.add(page, pageName);
        menuBar.addPage(pageName, menuMneumonic);
    }

    public void setPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public JMenu getMenu(){
        return menuBar.getMenu();
    }

    // setPageEnabled()
    // Also need to set the menuBar to disabled here too for the page...

}
