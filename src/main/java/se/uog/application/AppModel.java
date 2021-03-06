package se.uog.application;

import java.beans.PropertyChangeSupport;

import javax.swing.DefaultListModel;

import se.uog.course.AdminCourseTableModel;
import se.uog.course.CDCourseTableModel;
import se.uog.course.Course;
import se.uog.course.CourseTableModel;
import se.uog.course.PTTCourseTableModel;

import se.uog.qualification.Qualification;
import se.uog.qualification.QualificationTableModel;

import se.uog.teacher.Teacher;
import se.uog.teacher.TeacherTableModel;
import se.uog.training.Training;
import se.uog.training.TrainingTableModel;

import se.uog.user.User;

/**
 * The AppModel class, a wrapper containing the Core Object lists consumed by our application.
 */
public class AppModel {

    private DefaultListModel<Qualification> qualificationList;
    private QualificationTableModel qualificationTableModel;

    private DefaultListModel<Teacher> teacherList;
    private TeacherTableModel teacherTableModel;

    private DefaultListModel<Training> trainingList;
    private TrainingTableModel trainingTableModel;

    private DefaultListModel<Course> courseList;
    private CourseTableModel adminCourseTableModel;
    private CourseTableModel cdCourseTableModel;
    private CourseTableModel pttCourseTableModel;

    private User user;
    private PropertyChangeSupport propertyChangeSupport;


    /**
     * Creates an empty AppModel.
     */
    public AppModel() {

        // DefaultListModels so they can be used in Swing views
        // Don't have to write the observer pattern stuff for them, it's already done
        // This allows our list selectors to stay synchronised with our tables.

        qualificationList = new DefaultListModel<>();
        teacherList = new DefaultListModel<>();
        courseList = new DefaultListModel<>();
        trainingList = new DefaultListModel<>();

        // Create Object Table Models for our pages
        qualificationTableModel = new QualificationTableModel(qualificationList);
        teacherTableModel = new TeacherTableModel(teacherList, qualificationList, trainingList);
        pttCourseTableModel = new PTTCourseTableModel(courseList);
        cdCourseTableModel = new CDCourseTableModel(courseList, qualificationList);
        adminCourseTableModel = new AdminCourseTableModel(courseList, teacherList);
        trainingTableModel = new TrainingTableModel(trainingList, qualificationList);

        user = User.UNASSIGNED;
        propertyChangeSupport = new PropertyChangeSupport(user);

    }

    /**
     * Returns the PropertyChangeSupport object so listeners can be added.
     * 
     * @return
     */
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public User getUser() {
        return user;
    }

    /**
     * Sets the user and fires a propertyChange event to let the listeners that they need to update.
     * 
     * @param newUser
     */
    public void setUser(User newUser) {
        User oldUser = user;
        user = newUser;
        propertyChangeSupport.firePropertyChange("currentUser", oldUser, user);
    }


    // These methods return the table models required by the pages

    public TrainingTableModel getTrainingTableModel() {
        return trainingTableModel;
    }

    public QualificationTableModel getQualificationTableModel() {
        return qualificationTableModel;
    }

    public TeacherTableModel getTeacherTableModel() {
        return teacherTableModel;
    }

    public CourseTableModel getAdminCourseTableModel() {
        return adminCourseTableModel;
    }

    public CourseTableModel getCdCourseTableModel() {
        return cdCourseTableModel;
    }

    public CourseTableModel getPttCourseTableModel() {
        return pttCourseTableModel;
    }


    // These methods set / get the arrays from the DefaultListModels
    // for serialization / deserialization into a file format / for databases.

    public Qualification[] getQualificationArray() {
        Qualification[] array = new Qualification[qualificationList.getSize()];
        qualificationList.copyInto(array);
        return array;
    }

    public void setQualificationList(Qualification[] array) {
        populateDefaultListModelFromArray(qualificationList, array);
    }

    public Training[] getTrainingArray() {
        Training[] array = new Training[trainingList.getSize()];
        trainingList.copyInto(array);
        return array;
    }

    public void setTrainingList(Training[] array) {
        populateDefaultListModelFromArray(trainingList, array);
    }

    public Teacher[] getTeacherArray() {
        Teacher[] array = new Teacher[teacherList.getSize()];
        teacherList.copyInto(array);
        return array;
    }

    public void setTeacherList(Teacher[] array) {
        populateDefaultListModelFromArray(teacherList, array);
    }

    public Course[] getCourseArray() {
        Course[] array = new Course[courseList.getSize()];
        courseList.copyInto(array);
        return array;
    }

    public void setCourseList(Course[] array) {
        populateDefaultListModelFromArray(courseList, array);
    }

    // Helper methods for converting swing DefaultListModels to arrays.

    /**
     * Creates a swing DefaultListModel from an array
     *
     * @param <T>   The array type
     * @param array The array to convert to a DefaultListModel
     * @return A DefaultListModel populated by array
     */
    private <T> DefaultListModel<T> populateDefaultListModelFromArray(DefaultListModel<T> list,
            T[] array) {
        list.clear();
        for (T elem : array) {
            list.addElement(elem);
        }
        return list;
    }
}
