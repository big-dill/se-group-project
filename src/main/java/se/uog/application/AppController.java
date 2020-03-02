package se.uog.application;

import se.uog.TablePageView;
import se.uog.course.Course;
import se.uog.database.FileStorage;
import se.uog.database.JSONConverterUtil;
import se.uog.qualification.Qualification;
import se.uog.teacher.Teacher;
import se.uog.training.Training;
import se.uog.user.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class AppController implements PropertyChangeListener {

    private static final String JSON_MODEL_FILENAME = "model.json";
    private static final String LANDING_PAGE_CONSTRAINT = "Home";

    private final FileStorage appStorage;
    private final AppView appView;
    private AppModel appModel;
    private TablePageView qualificationPage;
    private TablePageView teacherPage;
    private TablePageView coursePage;
    private TablePageView trainingPage;

    public AppController() {

        // Load the model from the storage...
        this.appStorage = new FileStorage(JSON_MODEL_FILENAME);

        // If storage fails or no file exists, create a blank model...
        try {
            appModel = JSONConverterUtil.convertJSONToAppModel(appStorage.getJSON());
        } catch (IOException e) {
            System.err.println("Could not read model from file. Check for model.json in path.");
            System.err.println("Creating new model...");
            appModel = new AppModel();
        }

        this.appView = new AppView(this);

        appModel.getPropertyChangeSupport().addPropertyChangeListener(this);

        setupView();

        // Set visible only after setup
        appView.setVisible(true);
    }

    private void setupView() {

        JPanel homePage = new HomePage(this);
        appView.addPage(homePage, LANDING_PAGE_CONSTRAINT, KeyEvent.VK_H);

        qualificationPage = new TablePageView<Qualification>(appModel.getQualificationTableModel());
        appView.addPage(qualificationPage, "Qualifications", KeyEvent.VK_Q);

        teacherPage = new TablePageView<Teacher>(appModel.getTeacherTableModel());
        appView.addPage(teacherPage, "Teachers", KeyEvent.VK_T);

        coursePage = new TablePageView<Course>(appModel.getAdminCourseTableModel());
        appView.addPage(coursePage, "Courses", KeyEvent.VK_C);

        trainingPage = new TablePageView<Training>(appModel.getTrainingTableModel());
        appView.addPage(trainingPage, "Training", KeyEvent.VK_R);

        appView.addUserMenu(getUser());

        // NOTE:
        // You can create other 'tableModels' and dynamically switch them in using:
        // e.g. coursePage.setTableModel(appModel.getAdminCourseTableModel);
        // This will be useful for changing users!

        // We can probably use inheritance in some way and just override the column part
        // of the
        // model!
    }

    public void setPage(String pageName) {
        if (pageName.equals(LANDING_PAGE_CONSTRAINT)) {
            appModel.setUser(User.UNASSIGNED);
        }
        appView.setPage(pageName);
    }

    public void close() {
        // Try saving to storage. If it fails, print a stack trace.
        try {
            appStorage.storeJSON(JSONConverterUtil.convertAppModelToJSON(appModel));
        } catch (IOException e) {
            // Could probably prompt user for action here, rather than just flatly closing.
            System.err.println("IO error, could not store database in file.");
            e.printStackTrace();
        }

        // Quit the app
        System.exit(0);
    }

    public void setUser(User user) {
        appModel.setUser(user);
    }

    public User getUser() {
        return appModel.getUser();
    }

    // Updates the view on PropertyChangeEvent
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        // Resets the views to default
        qualificationPage.setTableEnabled(true);
        trainingPage.setTableEnabled(true);
        trainingPage.setTableEnabled(true);
        coursePage.setTableEnabled(true);


        appView.editUserMenu(getUser());
        appView.getMenu().setEnabled(true);

        switch (getUser()) {
            case DIRECTOR:
                coursePage.setTableModel(appModel.getPttCourseTableModel());
                trainingPage.setTableEnabled(false); // Removes the buttons too!
                teacherPage.setTableEnabled(false);
                break;
            case ADMINISTRATOR:
                coursePage.setTableModel(appModel.getAdminCourseTableModel());
                coursePage.setTableButtonsEnabled(false);
                break;
            case COURSE_DIRECTOR:
                coursePage.setTableModel(appModel.getCdCourseTableModel());
                trainingPage.setTableEnabled(false); // Removes the buttons too!
                teacherPage.setTableEnabled(false);
                break;
            default:
                appView.getMenu().setEnabled(false);
                appView.getMenu().repaint();
                break;
        }
    }
}


