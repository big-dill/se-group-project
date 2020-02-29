package se.uog.course;

import se.uog.qualification.Qualification;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CDCourseTableModel extends CourseTableModel {

    private List<ObjectTableColumn<Course>> columns = new ArrayList<>();
    private DefaultListModel<Qualification> qualificationList;

    public CDCourseTableModel(DefaultListModel<Course> courseList, DefaultListModel<Qualification> qualificationList) {
        super(courseList);
        this.qualificationList = qualificationList;

        // Setup Columns
        ObjectTableColumn<Course> courseDirectorColumn = new ObjectTableColumnBuilder<Course>()
            .setTitle("Course Director Name")
            .setClass(String.class)
            .setRowElementGetter(course -> course.getCourseDirectorName())
            .setRowElementSetter((course, val) -> course.setCourseDirectorName((String) val))
            .build();

        ObjectTableColumn<Course> nameColumn = new ObjectTableColumnBuilder<Course>()
                .setTitle("Course Title")
                .setClass(String.class)
                .setRowElementGetter(course -> course.getName())
                .setRowElementSetter((course, val) -> course.setName((String) val))
                .build();

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
                .setRowElementGetter(course -> course.getAssignedTeachers())
                .setEditable(false)
                .build();

        ObjectTableColumn<Course> isApprovedColumn = new ObjectTableColumnBuilder<Course>()
                .setTitle("Is Approved")
                .setClass(Boolean.class)
                .setRowElementGetter(course -> course.isApproved())
                .setEditable(false)
                .build();


        columns.add(courseDirectorColumn);
        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(teachersColumn);
        columns.add(isApprovedColumn);

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
        return columns;
    }

}
