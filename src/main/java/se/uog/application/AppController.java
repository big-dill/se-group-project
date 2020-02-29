package se.uog.application;

import se.uog.course.CoursePage;
import se.uog.database.FileStorage;
import se.uog.database.JSONConverterUtil;
import se.uog.qualification.QualificationPage;
import se.uog.teacher.TeacherPage;
import se.uog.training.TrainingPage;
import se.uog.user.User;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class AppController implements PropertyChangeListener {

    private static final String JSON_MODEL_FILENAME = "model.json";

    private final FileStorage appStorage;
    private final AppView appView;
    private AppModel appModel;
    private QualificationPage qualificationPage;
    private TeacherPage teacherPage;
    private CoursePage coursePage;
    private TrainingPage trainingPage;

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

        setupView();

        // Set visible only after setup
        appView.setVisible(true);
    }

    private void setupView() {

        JPanel homePage = new HomePage(this, appView);
        appView.addPage(homePage, "Home", KeyEvent.VK_H);

        qualificationPage = new QualificationPage(appModel.getQualificationTableModel(), this);
        appView.addPage(qualificationPage, "Qualifications", KeyEvent.VK_Q);

        teacherPage = new TeacherPage(appModel.getTeacherTableModel(), this);
        appView.addPage(teacherPage, "Teachers", KeyEvent.VK_T);

        coursePage = new CoursePage(appModel.getAdminCourseTableModel(), this);
        appView.addPage(coursePage, "Courses", KeyEvent.VK_C);

        trainingPage = new TrainingPage(appModel.getTrainingTableModel(), this);
        appView.addPage(trainingPage, "Training", KeyEvent.VK_R);

        // NOTE:
        // You can create other 'tableModels' and dynamically switch them in using:
        // e.g. coursePage.setTableModel(appModel.getAdminCourseTableModel);
        // This will be useful for changing users!

        // We can probably use inheritance in some way and just override the column part
        // of the
        // model!
    }

    public void setPage(String pageName) {
        if(pageName.equals("Home")){appModel.setUser(User.UNASSIGNED);}
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

    public void setUser(User user){
        appModel.setUser(user);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        appModel.getPropertyChangeSupport().addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        appModel.getPropertyChangeSupport().removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (!appModel.getUser().equals(User.UNASSIGNED)){
            appView.getMenu().setEnabled(true);
            appView.getMenu().repaint();
        } else
            {
            appView.getMenu().setEnabled(false);
            appView.getMenu().repaint();
        }

        switch(appModel.getUser()){
            case DIRECTOR:
                coursePage.setTableModel(appModel.getPttCourseTableModel());
                break;

            case ADMINISTRATOR:
                coursePage.setTableModel(appModel.getAdminCourseTableModel());
                break;

            case CLASS_DIRECTOR:
                coursePage.setTableModel(appModel.getCdCourseTableModel());
                break;
        }



    }
}


