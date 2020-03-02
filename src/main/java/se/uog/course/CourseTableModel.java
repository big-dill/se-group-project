package se.uog.course;

import java.util.List;

import javax.swing.DefaultListModel;

import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableModel;

public abstract class CourseTableModel implements ObjectTableModel<Course> {

    protected DefaultListModel<Course> courseList;

    public CourseTableModel(DefaultListModel<Course> courseList) {

        this.courseList = courseList;
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
    public abstract List<ObjectTableColumn<Course>> getObjectColumnMap();

}
