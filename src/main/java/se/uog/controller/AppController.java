package se.uog.controller;

import se.uog.appview.AppMenu;
import se.uog.appview.AppView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppController implements ActionListener {

    private final AppView appView;


    public AppController() {
        this.appView = new AppView(this);
    }


    @Override
    public void actionPerformed(final ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);

        switch (command) {

            case AppMenu.LOAD_HOME_PAGE:
                appView.setVisibleCard(AppMenu.LOAD_HOME_PAGE);
                System.out.println(command + " clicked");
                break;

            case AppMenu.LOAD_TEACHER_PAGE:
                appView.setVisibleCard(AppMenu.LOAD_TEACHER_PAGE);
                System.out.println(command + " clicked");
                break;

            case AppMenu.LOAD_COURSE_PAGE:
                appView.setVisibleCard(AppMenu.LOAD_COURSE_PAGE);
                System.out.println(command + " clicked");
                break;

            case AppMenu.LOAD_QUALIFICATION_PAGE:
                appView.setVisibleCard(AppMenu.LOAD_QUALIFICATION_PAGE);
                System.out.println(command + " clicked");
                break;

            case AppMenu.LOAD_TRAINING_PAGE:
                appView.setVisibleCard(AppMenu.LOAD_TRAINING_PAGE);
                System.out.println(command + " clicked");
                break;
                
            default:
        }
    }
}


