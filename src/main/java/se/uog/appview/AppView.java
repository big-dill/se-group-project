package se.uog.appview;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import se.uog.controller.AppController;

import se.uog.model.*;
import se.uog.appview.pages.*;

public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; // Obvs can be changed later
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

        // HACKS START HERE
        // ----------------
        AppModel model = new AppModel();

        TeacherTableModel teacherModel =
                new TeacherTableModel(model.getTeacherList(), model.getQualificationList());
        JPanel teacherPanel = new TeacherPage(teacherModel);

        setTeacherPanel(teacherPanel);
        mainPanel.add(teacherPanel, AppMenu.LOAD_TEACHER_PAGE);

        CourseTableModel courseModel = new CourseTableModel(model.getCourseList(),
                model.getTeacherList(), model.getQualificationList());
        JPanel coursePanel = new CoursePage(courseModel);

        setCoursePanel(coursePanel);
        mainPanel.add(coursePanel, AppMenu.LOAD_COURSE_PAGE);

        QualificationTableModel qualificationModel =
                new QualificationTableModel(model.getQualificationList());

        JPanel qualificationPanel = new QualificationPage(qualificationModel);

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

    private void setTrainingPanel(JPanel trainingPanel) {
        trainingPanel.setBackground(Color.GREEN);
    }

    public void setVisibleCard(String constraint) {
        cardLayout.show(mainPanel, constraint);
    }


}
