package se.uog.swing;

import javax.swing.DefaultListModel;
import se.uog.swing.table.RequirementTableConfiguration;
import se.uog.swing.table.TableModel;
import se.uog.swing.table.TeacherTableConfiguration;

import se.uog.swing.*;

/**
 * The source of EVERYTHING.
 */
public class AppModel {
    // Update this with actual Teacher objects (remember, toString() method needed!)
    private DefaultListModel<Teacher> teacherListModel;
    private TableModel<Teacher> teacherTableModel;

    private DefaultListModel<Requirement> requirementListModel;
    private TableModel<Requirement> requirementTableModel;

    public AppModel() {
        setupRequirementModels();
        setupTeacherModels();
    }

    private void setupRequirementModels() {
        requirementListModel = new DefaultListModel<Requirement>();
        requirementTableModel = new TableModel<Requirement>(requirementListModel,
                new RequirementTableConfiguration());
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
