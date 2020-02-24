package se.uog.model;

import javax.swing.DefaultListModel;

public class AppModel {
    // DefaultListModels so they can be used in Swing views
    // Don't have to write the observer pattern stuff for them, it's already done
    DefaultListModel<Qualification> qualificationList;
    QualificationTableModel qualificationTableModel;

    DefaultListModel<Teacher> teacherList;
    TeacherTableModel teacherTableModel;

    DefaultListModel<Course> courseList;
    CourseTableModel courseTableModel;


    public AppModel() {
        // TODO: ListModels need to be loaded and saved to the database at some point
        qualificationList = new DefaultListModel<>();
        teacherList = new DefaultListModel<>();
        courseList = new DefaultListModel<>();

        // Create Object Table Models for our view
        qualificationTableModel = new QualificationTableModel(qualificationList);
        teacherTableModel = new TeacherTableModel(teacherList, qualificationList);
        courseTableModel = new CourseTableModel(courseList, teacherList, qualificationList);
    }

    public QualificationTableModel getQualificationTableModel() {
        return qualificationTableModel;
    }

    public TeacherTableModel getTeacherTableModel() {
        return teacherTableModel;
    }

    public CourseTableModel getCourseTableModel() {
        return courseTableModel;
    }

}
