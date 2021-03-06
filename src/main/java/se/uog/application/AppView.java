package se.uog.application;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;

import se.uog.user.User;

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

    public void addUserMenu(User user) {
        menuBar.createUserInMenu(user);
    }

    public void editUserMenu(User user) {
        menuBar.updateUserInMenu(user);
    }

    public void setPage(String pageName) {
        cardLayout.show(mainPanel, pageName);
    }

    public JMenu getMenu() {
        return menuBar.getMenu();
    }

    // setPageEnabled()
    // Also need to set the menuBar to disabled here too for the page...

}
