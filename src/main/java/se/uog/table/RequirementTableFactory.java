package se.uog.table;

import java.util.ArrayList;
import java.util.List;
import se.uog.swing.Requirement;
import se.uog.swing.Teacher;

public class RequirementTableConfigFactory {

    final static int QUALIFICATION_COLUMN = 0;
    final static int TEACHER_COLUMN = 1;
    final static int APPROVED_COLUMN = 2;

    String[] columnNames = new String[] {"Qualification", "Teachers", "Approved"};
    Class<?>[] columnClasses = new Class<?>[] {String.class, List.class, Boolean.class};

    public RequirementTableConfigFactory() {
        ObjectTableColumn<Requirement> qualificationCol = new ObjectTableColumn<>("Qualification", // Col
                                                                                                   // name
                String.class, // Col class
                (element, value) -> element.setQualificationName((String) value), // Col setter
                element -> element.getQualificationName(), // Col getter
                true, // isEditable
                null // editor - null = default editor
        );

        ObjectTableColumn<Requirement> teachersCol = new ObjectTableColumn<>("Teachers", // Col name
                List.class, // Col class
                (element, value) -> element.setTeachers((List<Teacher>) value), // Col setter
                element -> element.getTeachers(), // Col getter
                true, // isEditable
                null // editor - null = default editor
        );
    }
}
