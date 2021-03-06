package se.uog.application;

import java.awt.event.KeyEvent;

import javax.swing.DefaultButtonModel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeListener;

import se.uog.user.User;

@SuppressWarnings("serial")
public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";

    private AppController appController;
    private JMenu menu;
    private JMenu currentUserMenu;

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
        currentUserMenu = new JMenu("Current user: " + user.toString());
        DefaultButtonModel model = (DefaultButtonModel) currentUserMenu.getModel();
        model.setArmed(false);
        for (ChangeListener cl : model.getChangeListeners()) {
            model.removeChangeListener(cl);
        }
        currentUserMenu.setEnabled(false);
        currentUserMenu.setFocusable(false);
        add(currentUserMenu);
    }

    public void updateUserInMenu(User user) {
        remove(currentUserMenu);
        revalidate();
        repaint();
        createUserInMenu(user);
    }

    public JMenu getMenu() {
        return menu;
    }

}
