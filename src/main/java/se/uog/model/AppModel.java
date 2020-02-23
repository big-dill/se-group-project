package se.uog.model;

import javax.swing.DefaultListModel;

/**
 * The source of EVERYTHING.
 */
public class AppModel {

    private DefaultListModel<ExampleTeacher> teacherListModel = new DefaultListModel<>();
    private DefaultListModel<ExampleQualification> qualificationListModel = new DefaultListModel<>();

    public DefaultListModel<ExampleTeacher> getTeacherListModel() {
        return teacherListModel;
    }

    public DefaultListModel<ExampleQualification> getQualificationListModel() {
        return qualificationListModel;
    }

}
