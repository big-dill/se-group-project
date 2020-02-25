package se.uog.model;

import javax.swing.DefaultListModel;

public class AppModel {
    // DefaultListModels so they can be used in Swing views
    // Don't have to write the observer pattern stuff for them, it's already done
    private QualificationTableModel qualificationTableModel;
    private TeacherTableModel teacherTableModel;
    private CourseTableModel courseTableModel;


    public AppModel() {
        // TODO: ListModels need to be loaded and saved to the database at some point
        DefaultListModel<Qualification> qualificationList = new DefaultListModel<>();
        DefaultListModel<Teacher> teacherList = new DefaultListModel<>();
        DefaultListModel<Course> courseList = new DefaultListModel<>();

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
