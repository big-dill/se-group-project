package se.uog.course;

import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PTTCourseTableModel extends CourseTableModel {

    private List<ObjectTableColumn<Course>> columns = new ArrayList<>();

    public PTTCourseTableModel(DefaultListModel<Course> courseList) {
        super(courseList);

        // Generate Columns
        ObjectTableColumn<Course> courseDirectorColumn = new ObjectTableColumnBuilder<Course>()
            .setTitle("Course Director Name")
            .setClass(String.class)
            .setRowElementGetter(course -> course.getCourseDirectorName())
            .setEditable(false)
            .build();

        ObjectTableColumn<Course> nameColumn = new ObjectTableColumnBuilder<Course>()
            .setTitle("Course Title")
            .setClass(String.class)
            .setRowElementGetter(course -> course.getName())
            .setEditable(false)
            .build();

        ObjectTableColumn<Course> qualificationsColumn = new ObjectTableColumnBuilder<Course>()
            .setTitle("Required Qualifications")
            .setClass(List.class)
            .setRowElementGetter(course -> course.getRequirements())
            .setEditable(false)
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
            .setRowElementSetter((course, val) -> {
                course.setApproved((boolean) val);
            })
            .build();


        columns.add(courseDirectorColumn);
        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(teachersColumn);
        columns.add(isApprovedColumn);
    }

    @Override
    public DefaultListModel<Course> getListModel() {
        return super.getListModel();
    }

    @Override
    public List<ObjectTableColumn<Course>> getObjectColumnMap() {


        return columns;
    }


}
