package se.uog.appview;

import se.uog.controller.AppController;

import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";

    private AppController appController;
    private JMenu menu;

    public AppMenu(AppController appController) {
        this.appController = appController;
        this.menu = new JMenu(MENU_NAME);

        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.
        add(menu);
    }

    public void addPage(String pageName, int mneumonic) {
        JMenuItem pageViewItem = new JMenuItem(pageName, mneumonic);
        pageViewItem.setMnemonic(mneumonic);

        // Set action listener here, so appController is clean
        pageViewItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appController.setPage(pageName);
            }
        });

        menu.add(pageViewItem);
    }

    // public void disablePage()
    // public void enablePage()

}
