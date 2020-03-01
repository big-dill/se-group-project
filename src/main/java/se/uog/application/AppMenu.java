package se.uog.application;

import se.uog.user.User;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";

    private AppController appController;
    private JMenu menu;
    private JMenuItem currentUserMenuItem;

    public AppMenu(AppController appController) {
        this.appController = appController;
        this.menu = new JMenu(MENU_NAME);

        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.
        menu.setEnabled(false);
        add(menu);
    }

    public void addPage(String pageName, int mneumonic) {
        JMenuItem pageViewItem = new JMenuItem(pageName, mneumonic);
        pageViewItem.setMnemonic(mneumonic);
        // Set action listener here, so appController is clean
        pageViewItem.addActionListener(e -> appController.setPage(pageName));

        menu.add(pageViewItem);
    }

    public void createUserInMenu(User user) {
        currentUserMenuItem = new JMenuItem("Current user: " + user.toString());
        DefaultButtonModel model = (DefaultButtonModel) currentUserMenuItem.getModel();
        model.setArmed(false);
        for (ChangeListener cl : model.getChangeListeners()) {
            model.removeChangeListener(cl);
        }
        currentUserMenuItem.setFocusable(false);
        menu.add(currentUserMenuItem);
    }

    public void upDateUserInMenu(User user) {
        menu.remove(currentUserMenuItem);
        createUserInMenu(user);
    }

    public JMenu getMenu() {
        return menu;
    }

    // public void disablePage()
    // public void enablePage()

}
