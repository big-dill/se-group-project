package se.appview;


import se.controller.MenuActionListener;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";
    public static final String LOAD_TEACHER_PAGE = "Teachers";
    public static final String LOAD_COURSE_PAGE = "Courses";
    private final MenuActionListener menuActionListener;


    public AppMenu(MenuActionListener menuActionListener) {

        this.menuActionListener = menuActionListener;

        JMenu menu = new JMenu(MENU_NAME);
        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.

        JMenuItem miLoadTeacherView = new JMenuItem(LOAD_TEACHER_PAGE, KeyEvent.VK_T);
        miLoadTeacherView.setMnemonic(KeyEvent.VK_T);
        miLoadTeacherView.addActionListener(menuActionListener);

        JMenuItem miLoadCourseView = new JMenuItem(LOAD_COURSE_PAGE);
        miLoadCourseView.setMnemonic(KeyEvent.VK_C);
        miLoadCourseView.addActionListener(menuActionListener);

        menu.add(miLoadTeacherView);
        menu.add(miLoadCourseView);

        add(menu);

    }

}
