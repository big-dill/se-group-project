package se.appview;

import se.controller.MenuActionListener;

import javax.swing.*;
import java.awt.*;


public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; //Obvs can be changed later
    private static final String WELCOME_TEXT = "Welcome";
    public static CardLayout layout = new CardLayout();
    private JPanel teacherPanel;
    private JPanel coursePanel;
    private JPanel mainPanel;


    public AppView(MenuActionListener menuActionListener) {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setJMenuBar(new AppMenu(menuActionListener));
        setSize(600, 400);

        JPanel mainPanel = new JPanel();
        this.mainPanel = mainPanel;
        mainPanel.setLayout(layout);

        JPanel landingMenu = new JPanel();
        setLandingPage(landingMenu);
        mainPanel.add(landingMenu, "3");



        JPanel teacherPanel = new JPanel();
        setTeacherPanel(teacherPanel);
        this.teacherPanel = teacherPanel;
        mainPanel.add(teacherPanel, "1");

        JPanel coursePanel = new JPanel();
        setCoursePanel(coursePanel);
        this.coursePanel = coursePanel;
        mainPanel.add(coursePanel, "2");

        layout.show(mainPanel, "3");

        add(mainPanel);
        setVisible(true);
    }

    private void setLandingPage(JPanel mainPanel){
        JLabel welcomeLabel = new JLabel(WELCOME_TEXT);
        mainPanel.add(welcomeLabel);
    }

    private void setTeacherPanel(JPanel teacherPanel){
        teacherPanel.setBackground(Color.red);
    }

    private void setCoursePanel(JPanel teacherPanel){
        teacherPanel.setBackground(Color.blue);
    }

    public static CardLayout getCardLayout() {
        return layout;
    }

    public JPanel getTeacherPanel() {
        return teacherPanel;
    }

    public JPanel getCoursePanel() {
        return coursePanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
