package se.uog.appview;


import se.uog.controller.AppController;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AppMenu extends JMenuBar {

    private static final String MENU_NAME = "Main menu";
    public static final String LOAD_HOME_PAGE = "Homepage";
    public static final String LOAD_TEACHER_PAGE = "Teachers";
    public static final String LOAD_QUALIFICATION_PAGE = "Qualifications";
    public static final String LOAD_TRAINING_PAGE = "Training";
    public static final String LOAD_COURSE_PAGE = "Courses";


    AppMenu(AppController appController) {

        JMenu menu = new JMenu(MENU_NAME);
        menu.setMnemonic(KeyEvent.VK_M); // Uses a to open the menu. Press Alt + M.

        JMenuItem miLoadMainView = new JMenuItem(LOAD_HOME_PAGE, KeyEvent.VK_M);
        miLoadMainView.setMnemonic(KeyEvent.VK_H);
        miLoadMainView.addActionListener(appController);

        JMenuItem miLoadTeacherView = new JMenuItem(LOAD_TEACHER_PAGE, KeyEvent.VK_T);
        miLoadTeacherView.setMnemonic(KeyEvent.VK_T);
        miLoadTeacherView.addActionListener(appController);

        JMenuItem miLoadQualificationView = new JMenuItem(LOAD_QUALIFICATION_PAGE, KeyEvent.VK_Q);
        miLoadQualificationView.setMnemonic(KeyEvent.VK_Q);
        miLoadQualificationView.addActionListener(appController);

        JMenuItem miLoadTrainingsView = new JMenuItem(LOAD_TRAINING_PAGE, KeyEvent.VK_R);
        miLoadTrainingsView.setMnemonic(KeyEvent.VK_R);
        miLoadTrainingsView.addActionListener(appController);

        JMenuItem miLoadCourseView = new JMenuItem(LOAD_COURSE_PAGE, KeyEvent.VK_C);
        miLoadCourseView.setMnemonic(KeyEvent.VK_C);
        miLoadCourseView.addActionListener(appController);

        menu.add(miLoadMainView);
        menu.add(miLoadTeacherView);
        menu.add(miLoadCourseView);
        menu.add(miLoadQualificationView);
        menu.add(miLoadTrainingsView);

        add(menu);

    }

}
