package se.uog.model;

import javax.swing.DefaultListModel;

public class AppModel {
    // DefaultListModels so they can be used in Swing views
    // Don't have to write the observer pattern stuff for them, it's already done
    DefaultListModel<Qualification> qualificationList;
    DefaultListModel<Teacher> teacherList;
    DefaultListModel<Course> courseList;

    // This might need setters to load from the database at some point...
    public AppModel() {
        qualificationList = new DefaultListModel<>();
        teacherList = new DefaultListModel<>();
        courseList = new DefaultListModel<>();
    }

    public DefaultListModel<Qualification> getQualificationList() {
        return qualificationList;
    }

    public DefaultListModel<Teacher> getTeacherList() {
        return teacherList;
    }

    public DefaultListModel<Course> getCourseList() {
        return courseList;
    }

}
