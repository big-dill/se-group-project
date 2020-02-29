package se.uog.application;

import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JPanel;
import se.uog.TablePageView;
import se.uog.course.Course;
import se.uog.database.FileStorage;
import se.uog.database.JSONConverterUtil;
import se.uog.qualification.Qualification;
import se.uog.teacher.Teacher;
import se.uog.training.Training;

public class AppController {

    private static final String JSON_MODEL_FILENAME = "model.json";

    private final FileStorage appStorage;
    private final AppView appView;
    private AppModel appModel;

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

        JPanel homePage = new HomePage();
        appView.addPage(homePage, "Home", KeyEvent.VK_H);

        JPanel qualificationPage =
                new TablePageView<Qualification>(appModel.getQualificationTableModel());
        appView.addPage(qualificationPage, "Qualifications", KeyEvent.VK_Q);

        JPanel teacherPage = new TablePageView<Teacher>(appModel.getTeacherTableModel());
        appView.addPage(teacherPage, "Teachers", KeyEvent.VK_T);

        JPanel coursePage = new TablePageView<Course>(appModel.getAdminCourseTableModel());
        appView.addPage(coursePage, "Courses", KeyEvent.VK_C);

        JPanel trainingPage = new TablePageView<Training>(appModel.getTrainingTableModel());
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

}
