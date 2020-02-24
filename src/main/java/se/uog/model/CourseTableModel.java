package se.uog.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import se.uog.table.*;

public class CourseTableModel implements ObjectTableConfig<Course> {

    DefaultListModel<Course> courseList;
    DefaultListModel<Qualification> qualificationList;
    DefaultListModel<Teacher> teacherList;

    public CourseTableModel(DefaultListModel<Course> courseList, DefaultListModel<Teacher> teacherList,
            DefaultListModel<Qualification> qualificationList) {

        this.courseList = courseList;
        this.teacherList = teacherList;
        this.qualificationList = qualificationList;
    }

    @Override
    public DefaultListModel<Course> getListModel() {
        return courseList;
    }

    @Override
    public Course createDefaultElement() {
        return new Course("");
    }

    @Override
    public List<ObjectTableColumn<Course>> getObjectColumnMap() {
        List<ObjectTableColumn<Course>> columns = new ArrayList<>();

        ObjectTableColumn<Course> nameColumn = new ObjectTableColumnBuilder<Course>()
                .setTitle("Course Title")
                .setClass(String.class)
                .setRowElementGetter(course -> course.getName())
                .setRowElementSetter((course, val) -> course.setName((String) val)).build();

        ObjectTableColumn<Course> qualificationsColumn = new ObjectTableColumnBuilder<Course>()
                .setTitle("Required Qualifications")
                .setClass(List.class)
                .setRowElementGetter(course -> course.getRequirements())
                .setRowElementSetter((course, val) -> {
                    // Set the qualifications here
                    course.setRequirements((List<Qualification>)val);
                })
                .setCellEditor(new ObjectTableListSelector<Course, Qualification>(qualificationList, "Select Qualifications"))
                .build();

        ObjectTableColumn<Course> teachersColumn = new ObjectTableColumnBuilder<Course>()
                .setTitle("Designated Teachers")
                .setClass(List.class)
                .setRowElementGetter(course -> course.getAssignedTeacher())
                .setRowElementSetter((course, val) -> {

                })
                .setCellEditor(new ObjectTableListSelector<Course, Teacher>(teacherList, "Select Teacher(s):"))
                .build();

        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(teachersColumn);
        return columns;
    }
}
