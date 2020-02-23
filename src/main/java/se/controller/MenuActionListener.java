package se.controller;

import se.appview.AppMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuActionListener implements ActionListener {


    @Override
    public void actionPerformed(final ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);

        switch (command) {

            case AppMenu.LOAD_TEACHER_PAGE:
                //showTeacherView();
                System.out.println(command + " clicked");
                break;
            case AppMenu.LOAD_COURSE_PAGE:
                //showCoursesView();
                System.out.println(command + " clicked");
                break;

            default:
        }
    }
}


