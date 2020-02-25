package se.uog.appview;


import se.uog.controller.AppController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";

    private AppController appController;
    private JMenu menu;

    AppMenu(AppController appController) {
        this.appController = appController;
        this.menu = new JMenu(MENU_NAME);

        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.
        add(menu);
    }

    void addPage(String pageName, int mneumonic) {
        JMenuItem pageViewItem = new JMenuItem(pageName, mneumonic);
        pageViewItem.setMnemonic(mneumonic);
        pageViewItem.addActionListener(appController);
        menu.add(pageViewItem);
    }

    void toggleEnabled(){
        if (menu.isEnabled()) {
            menu.setEnabled(false);
        }
        else {menu.setEnabled(true);}
    }


    // public void disablePage()
    // public void enablePage()

}
