package se.uog.model;

import javax.swing.DefaultListModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The AppModel class, a wrapper containing the Core Object lists consumed by
 * our application.
 */
public class AppModel {

    private DefaultListModel<Qualification> qualificationList;
    private QualificationTableModel qualificationTableModel;

    private DefaultListModel<Teacher> teacherList;
    private TeacherTableModel teacherTableModel;

    private DefaultListModel<Training> trainingList;
    private TrainingTableModel trainingTableModel;

    private DefaultListModel<Course> courseList;
    private CourseTableModel courseTableModel;

    private UserEnum currentUser = UserEnum.UNASSIGNED;
    private final PropertyChangeSupport propertyChangeSupport;


    /**
     * Creates an empty AppModel.
     */
    public AppModel() {

        this.propertyChangeSupport = new PropertyChangeSupport(currentUser);



        // DefaultListModels so they can be used in Swing views
        // Don't have to write the observer pattern stuff for them, it's already done
        // This allows our list selectors to stay synchronised with our tables.

        qualificationList = new DefaultListModel<>();
        teacherList = new DefaultListModel<>();
        courseList = new DefaultListModel<>();
        trainingList = new DefaultListModel<>();

        // Create Object Table Models for our pages
        qualificationTableModel = new QualificationTableModel(qualificationList);
        teacherTableModel = new TeacherTableModel(teacherList, qualificationList);
        courseTableModel = new CourseTableModel(courseList, teacherList, qualificationList);
        trainingTableModel = new TrainingTableModel(trainingList, qualificationList, teacherList);
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

    public CourseTableModel getCourseTableModel() {
        return courseTableModel;
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
    private <T> DefaultListModel<T> populateDefaultListModelFromArray(DefaultListModel<T> list, T[] array) {
        list.clear();
        for (T elem : array) {
            list.addElement(elem);
        }
        return list;
    }

    public UserEnum getCurrentUser() {
        return currentUser;
    }

    public void setUserEnum(UserEnum userEnum) {
        System.out.println("ffiring");
        UserEnum oldNEnum =  this.currentUser;
        this.currentUser = userEnum;
        propertyChangeSupport.firePropertyChange("currentUser", oldNEnum, userEnum);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
