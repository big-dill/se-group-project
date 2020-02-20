package se.uog.swing;

import javax.swing.DefaultListModel;

/**
 * The source of EVERYTHING.
 */
public class AppModel {
    // Update this with actual Teacher objects (remember, toString() method needed!)
    private DefaultListModel<Teacher> teacherListModel;
    private TableModel<Teacher> teacherTableModel;

    public AppModel() {
        setupRequirementModels();
        setupTeacherModels();
    }

    private void setupRequirementModels() {


    }

    private void setupTeacherModels() {
        teacherListModel = new DefaultListModel<Teacher>();
        teacherTableModel =
                new TableModel<Teacher>(teacherListModel, new TeacherTableConfiguration());
    }

    public TableModel<Teacher> getTeacherTableModel() {
        return teacherTableModel;
    }

    public DefaultListModel<Teacher> getTeacherListModel() {
        return teacherListModel;
    }

    public TableModel<Requirement> getRequirementTableModel() {
        return requirementTableModel;
    }

    public DefaultListModel<Requirement> getRequirementListModel() {
        return requirementListModel;
    }

}
