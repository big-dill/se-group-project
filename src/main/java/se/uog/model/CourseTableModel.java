package se.uog.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.DefaultListModel;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;
import se.uog.table.ObjectTableModel;

public class CourseTableModel implements ObjectTableModel<Course> {

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

        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(teachersColumn);
        return columns;
    }

    private BiFunction<List<Teacher>, Course, List<Teacher>> getTeacherColumnFilterFunction() {
        // Create filter which will be consumed by the ObjectTableListSelector. 
        // This only displays teachers with the relevant qualifications.

         return (teacherList, course) -> {
            
            // For each Teacher in our Teacher list
            Iterator<Teacher> iterator = teacherList.iterator();
            
            while(iterator.hasNext()) {
                List<Qualification> requiredQualifications = course.getRequirements();
                List<Qualification> teachersQualifications = iterator.next().getQualifications();
          
                HashSet<Qualification> hset= new HashSet<>(); 
                boolean hasRequiredQualifications = true;

                // Add all the requirements to a hashset
                for(Qualification q : teachersQualifications) {
                    if(!hset.contains(q)) {
                        hset.add(q); 
                    }   
                }
                // If at any point, the hash does not contain one of the teacher's qualifications...
                for(Qualification q: requiredQualifications) {
                    if(!hset.contains(q))
                        // They are not good enough!
                        hasRequiredQualifications = false; 
                        break;
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
