package se.uog.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import se.uog.appview.AppView;

import se.uog.appview.pages.CoursePage;
import se.uog.appview.pages.HomePage;
import se.uog.appview.pages.PageView;
import se.uog.appview.pages.QualificationPage;
import se.uog.appview.pages.TeacherPage;

import se.uog.model.AppModel;


public class AppController implements ActionListener {

    private final AppView appView;
    private AppModel appModel;

    public AppController(AppModel model) {
        this.appModel = model;
        this.appView = new AppView(this);

        setupPages();

        // Set visible only after setup
        appView.setVisible(true);
    }

    private void setupPages() {

        // Qualification Page

        PageView homePage = new HomePage();
        appView.addPage(homePage, KeyEvent.VK_H);

        PageView qualificationPage = new QualificationPage(appModel.getQualificationTableModel());
        appView.addPage(qualificationPage, KeyEvent.VK_Q);

        PageView teacherPage = new TeacherPage(appModel.getTeacherTableModel());
        appView.addPage(teacherPage, KeyEvent.VK_T);

        PageView coursePage = new CoursePage(appModel.getCourseTableModel());
        appView.addPage(coursePage, KeyEvent.VK_C);


        // NOTE:
        // You can create other 'tableModels' and dynamically switch them in using:
        // e.g. coursePage.setTableModel(appModel.getAdminCourseTableModel);
        // This will be useful for changing users!

        // We can probably use inheritance in some way and just override the column part of the
        // model!
    }


    @Override
    public void actionPerformed(final ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        // Command will be PAGE_ID, so no need for switch, just set the page to the pageID.
        appView.setPage(command);
    }
}


