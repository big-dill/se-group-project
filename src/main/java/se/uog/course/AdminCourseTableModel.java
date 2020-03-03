package se.uog.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

import javax.swing.DefaultListModel;

import se.uog.qualification.Qualification;

import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;

import se.uog.teacher.Teacher;

public class AdminCourseTableModel extends CourseTableModel {

    private List<ObjectTableColumn<Course>> columns = new ArrayList<>();

    public AdminCourseTableModel(DefaultListModel<Course> courseList, DefaultListModel<Teacher> teacherList) {
        super(courseList);

        // Setup Columns
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

        @SuppressWarnings("unchecked")
        ObjectTableColumn<Course> teachersColumn = new ObjectTableColumnBuilder<Course>()
            .setTitle("Designated Teachers")
            .setClass(List.class)
            .setRowElementGetter(course -> course.getAssignedTeachers())
            .setRowElementSetter((course, val) -> {
                course.setAssignedTeachers((List<Teacher>) val);
            })
            .setCellEditor(new ObjectTableListSelector<Course, Teacher>(
                teacherList,
                courseList,
                "Select Teacher(s):",
                getTeacherColumnFilterFunction() // This is from the private method below.
            ))
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

    /**
     * Provides the filter function for the teachersColumn cell editor. Reimplements
     * Julia's algorithm.
     *
     * @return the filter function
     */
    private BiFunction<List<Teacher>, Course, List<Teacher>> getTeacherColumnFilterFunction() {
        // Create filter which will be consumed by the ObjectTableListSelector.
        // This only displays teachers with the relevant qualifications.

        return (teacherList, course) -> {

            // For each Teacher in our Teacher list
            Iterator<Teacher> iterator = teacherList.iterator();

            while (iterator.hasNext()) {
                List<Qualification> requiredQualifications = course.getRequirements();
                List<Qualification> teachersQualifications = iterator.next().getQualifications();

                boolean hasRequiredQualifications = true;
                // Julia's algorithm:
                // Checks if each of the required qualifications exists in the teacher's
                // skillset.
                for (Qualification f : requiredQualifications) {
                    if (!teachersQualifications.contains(f)) {
                        hasRequiredQualifications = false;
                        break;
                    }
                }

                if (!hasRequiredQualifications) {
                    // Remove if they do not have the right stuff!
                    iterator.remove();
                }
            }
            return teacherList;
        };
    }
}
