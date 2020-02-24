package se.uog.appview;

import se.uog.controller.AppController;

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
        mainPanel.add(landingMenu, AppMenu.LOAD_HOME_PAGE);

        JPanel teacherPanel = new JPanel();
        setTeacherPanel(teacherPanel);
        mainPanel.add(teacherPanel, AppMenu.LOAD_TEACHER_PAGE);

        JPanel coursePanel = new JPanel();
        setCoursePanel(coursePanel);
        mainPanel.add(coursePanel, AppMenu.LOAD_COURSE_PAGE);

        JPanel qualificationPanel = new JPanel();
        setQualificationPanel(qualificationPanel);
        mainPanel.add(qualificationPanel, AppMenu.LOAD_QUALIFICATION_PAGE);

        JPanel trainingPanel = new JPanel();
        setTrainingPanel(trainingPanel);
        mainPanel.add(trainingPanel, AppMenu.LOAD_TRAINING_PAGE);



        cardLayout.show(mainPanel, AppMenu.LOAD_HOME_PAGE);

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

    private void setCoursePanel(JPanel coursePanel) {
        coursePanel.setBackground(Color.blue);
    }

    private void setQualificationPanel(JPanel qualificationPanel) {
        qualificationPanel.setBackground(Color.CYAN);
    }

    private void setTrainingPanel(JPanel trainingPanel){trainingPanel.setBackground(Color.GREEN);}

    public void setVisibleCard(String constraint) {
        cardLayout.show(mainPanel, constraint);
    }


}
