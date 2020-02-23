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

            case AppMenu.FIRST_MENU_ITEM:
                //showTeacherView();
                System.out.println("First item clicked");
                break;
            case AppMenu.SECOND_MENU_ITEM:
                //showCoursesView();
                System.out.println("Second item clicked");
                break;

            default:
        }
    }
}


