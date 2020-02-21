package se.uog.swing;

import javax.swing.DefaultListModel;
import se.uog.table.ObjectTableModel;
import se.uog.table.RequirementTableConfiguration;
import se.uog.table.TeacherTableConfiguration;

/**
 * The source of EVERYTHING.
 */
public class AppModel {
    // Update this with actual Teacher objects (remember, toString() method needed!)
    private DefaultListModel<Teacher> teacherListModel;
    private ObjectTableModel<Teacher> teacherTableModel;

    private DefaultListModel<Requirement> requirementListModel;
    private ObjectTableModel<Requirement> requirementTableModel;

    public AppModel() {
        setupRequirementModels();
        setupTeacherModels();
    }

    private void setupRequirementModels() {
        requirementListModel = new DefaultListModel<Requirement>();
        requirementTableModel = new ObjectTableModel<Requirement>(requirementListModel,
                new RequirementTableConfiguration());
    }

    private void setupTeacherModels() {
        teacherListModel = new DefaultListModel<Teacher>();
        teacherTableModel =
                new ObjectTableModel<Teacher>(teacherListModel, new TeacherTableConfiguration());
    }

    public ObjectTableModel<Teacher> getTeacherTableModel() {
        return teacherTableModel;
    }

    public DefaultListModel<Teacher> getTeacherListModel() {
        return teacherListModel;
    }

    public ObjectTableModel<Requirement> getRequirementTableModel() {
        return requirementTableModel;
    }

    public DefaultListModel<Requirement> getRequirementListModel() {
        return requirementListModel;
    }


}
