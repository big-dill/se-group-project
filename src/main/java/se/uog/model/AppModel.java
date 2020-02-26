package se.uog.model;

import javax.swing.DefaultListModel;

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
        teacherTableModel = new TeacherTableModel(teacherList, qualificationList);
        courseTableModel = new CourseTableModel(courseList, teacherList, qualificationList);
        trainingTableModel = new TrainingTableModel(trainingList, qualificationList);
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
        return getDefaultListModelArray(qualificationList);
    }

    public void setQualificationList(Qualification[] array) {
        qualificationList = createDefaultListModelFromArray(array);
    }

    public Training[] getTrainingArray() {
        return getDefaultListModelArray(trainingList);
    }

    public void setTrainingList(Training[] array) {
        trainingList = createDefaultListModelFromArray(array);
    }

    public Teacher[] getTeacherArray() {
        return getDefaultListModelArray(teacherList);
    }

    public void setTeacherList(Teacher[] array) {
        teacherList = createDefaultListModelFromArray(array);
    }

    public Course[] getCourseArray() {
        return getDefaultListModelArray(courseList);
    }

    public void setCourseList(Course[] array) {
        courseList = createDefaultListModelFromArray(array);
    }

    // Helper methods for converting swing DefaultListModels to arrays.

    /**
     * Converts a swing DefaultListModel into an array
     *
     * @param <T>       The type of DefaultListModel and the returned array
     * @param listModel The DefaultListModel to convert
     * @return An array of type <T> of the elements in listModel
     */
    private <T> T[] getDefaultListModelArray(DefaultListModel<T> listModel) {
        Object[] array = new Object[listModel.getSize()];
        listModel.copyInto(array);
        return (T[]) array;
    }

    /**
     * Creates a swing DefaultListModel from an array
     *
     * @param <T>   The array type
     * @param array The array to convert to a DefaultListModel
     * @return A DefaultListModel populated by array
     */
    private <T> DefaultListModel<T> createDefaultListModelFromArray(T[] array) {
        DefaultListModel<T> listModel = new DefaultListModel<>();
        for (T elem : array) {
            listModel.addElement(elem);
        }
        return listModel;
    }

}
