package se.uog.model;

import javax.swing.DefaultListModel;

public class AppModel {
    // DefaultListModels so they can be used in Swing views
    // Don't have to write the observer pattern stuff for them, it's already done
    private DefaultListModel<Qualification> qualificationList;
    private QualificationTableModel qualificationTableModel;

    private DefaultListModel<Teacher> teacherList;
    private TeacherTableModel teacherTableModel;
   
    private DefaultListModel<Training> trainingList;
    private TrainingTableModel trainingTableModel;
    
    private DefaultListModel<Course> courseList;
    private CourseTableModel courseTableModel;

    public AppModel() {
        // TODO: ListModels need to be loaded and saved to the database at some point
        qualificationList = new DefaultListModel<>();
        teacherList = new DefaultListModel<>();
        courseList = new DefaultListModel<>();
        trainingList = new DefaultListModel<>();

        // Create Object Table Models for our view
        qualificationTableModel = new QualificationTableModel(qualificationList);
        teacherTableModel = new TeacherTableModel(teacherList, qualificationList);
        courseTableModel = new CourseTableModel(courseList, teacherList, qualificationList);
        trainingTableModel = new TrainingTableModel(trainingList, qualificationList);
    }

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

    public Qualification[] getQualificationArray() {
        Qualification[] array = new Qualification[qualificationList.getSize()];
        qualificationList.copyInto(array);
        return array;
    }

    public void setQualificationList(Qualification[] array) {
        qualificationList.clear();
        for (Qualification q : array) {
            qualificationList.addElement(q);
        }
    }

    public Training[] getTrainingArray() {
        Training[] array = new Training[trainingList.getSize()];
        trainingList.copyInto(array);
        return array;
    }

    public void setTrainingList(Training[] array) {
        trainingList.clear();
        for (Training q : array) {
            trainingList.addElement(q);
        }
    }


    

    public Teacher[] getTeacherArray() {
        Teacher[] array = new Teacher[teacherList.getSize()];
        teacherList.copyInto(array);
        return array;
    }

    public void setTeacherList(Teacher[] array) {
        teacherList.clear();
        for (Teacher t : array) {
            teacherList.addElement(t);
        }
    }

    public Course[] getCourseArray() {
        Course[] array = new Course[courseList.getSize()];
        courseList.copyInto(array);
        return array;
    }

    public void setCourseList(Course[] array) {
        courseList.clear();
        for (Course c : array) {
            courseList.addElement(c);
        }
    }

}
