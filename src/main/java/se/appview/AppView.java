package se.appview;

import se.controller.AppController;

import javax.swing.*;
import java.awt.*;


public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; //Obvs can be changed later
    private static final String WELCOME_TEXT = "Welcome";
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel;

    public AppView(AppController appController) {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setJMenuBar(new AppMenu(appController));
        setSize(600, 400);

        JPanel mainPanel = new JPanel();
        this.mainPanel = mainPanel;
        mainPanel.setLayout(cardLayout);

        JPanel landingMenu = new JPanel();
        setLandingPage(landingMenu);
        mainPanel.add(landingMenu, WELCOME_TEXT);

        JPanel teacherPanel = new JPanel();
        setTeacherPanel(teacherPanel);
        mainPanel.add(teacherPanel, AppMenu.LOAD_TEACHER_PAGE);

        JPanel coursePanel = new JPanel();
        setCoursePanel(coursePanel);

        mainPanel.add(coursePanel, AppMenu.LOAD_COURSE_PAGE);

        cardLayout.show(mainPanel, WELCOME_TEXT);

        add(mainPanel);
        setVisible(true);
    }

    private void setLandingPage(JPanel mainPanel) {
        JLabel welcomeLabel = new JLabel(WELCOME_TEXT);
        mainPanel.add(welcomeLabel);
    }

    private void setTeacherPanel(JPanel teacherPanel) {
        teacherPanel.setBackground(Color.red);
    }

    private void setCoursePanel(JPanel teacherPanel) {
        teacherPanel.setBackground(Color.blue);
    }

    public void setVisibleCard(String constraint) {
        cardLayout.show(mainPanel, constraint);
    }


}
