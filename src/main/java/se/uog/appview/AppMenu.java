package se.uog.appview;


import se.uog.controller.AppController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";

    AppController appController;

    public AppMenu(AppController appController) {
        this.appController = appController;

        JMenu menu = new JMenu(MENU_NAME);
        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.
        add(menu);
    }

    public void addPage(String pageID, int mneumonic) {
        JMenuItem pageViewItem = new JMenuItem(pageID, mneumonic);
        pageViewItem.setMnemonic(mneumonic);
        pageViewItem.addActionListener(appController);
        this.add(pageViewItem);
    }

    // public void disablePage()
    // public void enablePage()

}
