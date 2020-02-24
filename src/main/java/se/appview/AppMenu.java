package se.appview;


import se.controller.AppController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";
    public static final String LOAD_TEACHER_PAGE = "Teachers";
    public static final String LOAD_COURSE_PAGE = "Courses";


     AppMenu(AppController appController) {

        JMenu menu = new JMenu(MENU_NAME);
        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.

        JMenuItem miLoadTeacherView = new JMenuItem(LOAD_TEACHER_PAGE, KeyEvent.VK_T);
        miLoadTeacherView.setMnemonic(KeyEvent.VK_T);
        miLoadTeacherView.addActionListener(appController);

        JMenuItem miLoadCourseView = new JMenuItem(LOAD_COURSE_PAGE);
        miLoadCourseView.setMnemonic(KeyEvent.VK_C);
        miLoadCourseView.addActionListener(appController);

        menu.add(miLoadTeacherView);
        menu.add(miLoadCourseView);

        add(menu);

    }

}
