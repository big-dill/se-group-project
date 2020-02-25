package se.uog.controller;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import se.uog.appview.AppView;
import se.uog.appview.pages.CoursePage;
import se.uog.appview.pages.QualificationPage;
import se.uog.appview.pages.TeacherPage;
import se.uog.model.AppModel;
import se.uog.model.AppModelSerializer;

public class AppController {

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
        JPanel qualificationPage = new QualificationPage(appModel.getQualificationTableModel());
        appView.addPage(qualificationPage, "Qualifications", KeyEvent.VK_Q);

        JPanel teacherPage = new TeacherPage(appModel.getTeacherTableModel());
        appView.addPage(teacherPage, "Teachers", KeyEvent.VK_T);

        JPanel coursePage = new CoursePage(appModel.getCourseTableModel());
        appView.addPage(coursePage, "Courses", KeyEvent.VK_C);

        // NOTE:
        // You can create other 'tableModels' and dynamically switch them in using:
        // e.g. coursePage.setTableModel(appModel.getAdminCourseTableModel);
        // This will be useful for changing users!

        // We can probably use inheritance in some way and just override the column part
        // of the
        // model!
    }

    public void setPage(String pageName) {
        appView.setPage(pageName);
    }

    public void close() {
        Gson gson = new GsonBuilder().registerTypeAdapter(AppModel.class, new AppModelSerializer()).create();
        try {
            FileWriter writer = new FileWriter("model.json");
            gson.toJson(appModel, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Quit the app
        System.exit(0);
    }

}
