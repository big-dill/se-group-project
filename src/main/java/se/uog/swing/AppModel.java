package se.uog.swing;

import javax.swing.DefaultListModel;

/**
 * The source of EVERYTHING.
 */
public class AppModel {
    // Update this with actual Teacher objects (remember, toString() method needed!)
    private DefaultListModel<Teacher> teacherListModel;
    private TableModel<Teacher> teacherTableModel;

    private DefaultListModel<Requirement> requirementListModel;
    private RequirementTableHeaders courseDirectorRequirementHeaders;
    private RequirementTableHeaders adminRequirementHeaders;
    private TableModel<Requirement> requirementTableModel;

    public AppModel() {
        setupRequirementModels();
        setupTeacherModels();
    }

    private void setupRequirementModels() {
        requirementListModel = new DefaultListModel<Requirement>();
        courseDirectorRequirementHeaders = new CDRequirementTableHeaders();
        adminRequirementHeaders = new AdminRequirementTableHeaders();
        requirementTableModel = new TableModel<Requirement>(requirementListModel, null);
    }

    private void setupTeacherModels() {
        teacherListModel = new DefaultListModel<Teacher>();
        teacherTableModel = new TableModel<Teacher>(teacherListModel, new TeacherTableHeaders());
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

    public void setAdministrator() {
        requirementTableModel.setHeaderConfiguration(adminRequirementHeaders);
    }

    public void setCourseDirector() {
        requirementTableModel.setHeaderConfiguration(courseDirectorRequirementHeaders);
    }

}
