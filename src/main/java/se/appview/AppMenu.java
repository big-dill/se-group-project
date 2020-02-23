package se.appview;


import se.controller.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";
    public static final String FIRST_MENU_ITEM = "Teachers";
    public static final String SECOND_MENU_ITEM = "Courses";
    private MenuActionListener mal = new MenuActionListener();


    public AppMenu() {

        JMenu menu = new JMenu(MENU_NAME);
        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.

        JMenuItem firstMenuItem = new JMenuItem(FIRST_MENU_ITEM, KeyEvent.VK_T);
        firstMenuItem.setMnemonic(KeyEvent.VK_T);
        firstMenuItem.addActionListener(mal);

        JMenuItem secondMenuItem = new JMenuItem(SECOND_MENU_ITEM);
        secondMenuItem.setMnemonic(KeyEvent.VK_C);
        secondMenuItem.addActionListener(mal);

        menu.add(firstMenuItem);
        menu.add(secondMenuItem);

        add(menu);

    }

}
