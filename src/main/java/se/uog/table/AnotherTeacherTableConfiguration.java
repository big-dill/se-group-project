package se.uog.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import se.uog.model.ExampleQualification;
import se.uog.model.ExampleTeacher;

public class AnotherTeacherTableConfiguration extends ExampleTeacherTableConfiguration {

    private List<ObjectTableColumn<ExampleTeacher>> objectColumnMap = new ArrayList<>();

    public AnotherTeacherTableConfiguration(DefaultListModel<ExampleTeacher> teacherList,
            DefaultListModel<ExampleQualification> qualificationList) {
        super(teacherList, qualificationList);
        initialiseObjectColumnMap();
    }

    @Override
    public List<ObjectTableColumn<ExampleTeacher>> getObjectColumnMap() {
        return objectColumnMap;
    }

    /**
     * Create the mapping between columns and object variables
     */
    private void initialiseObjectColumnMap() {

        ObjectTableColumn<ExampleTeacher> nameColumn =
            new ObjectTableColumnBuilder<ExampleTeacher>()
                .setTitle("Name")
                .setClass(String.class)
                .setRowElementGetter((teacher) -> teacher.getName())
                .setEditable(false)
                .build();


        ObjectTableColumn<ExampleTeacher> isCoolColumn =
            new ObjectTableColumnBuilder<ExampleTeacher>()
                .setTitle("Is Cool?")
                .setClass(Boolean.class)
                .setRowElementGetter((teacher) -> teacher.isCool())
                .setEditable(false)
                .build();

        objectColumnMap.add(nameColumn);
        objectColumnMap.add(isCoolColumn);
    }
}
