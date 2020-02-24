package se.uog.table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.DefaultListModel;
import se.uog.model.ExampleQualification;
import se.uog.model.ExampleTeacher;

public class ExampleTeacherTableConfiguration implements ObjectTableConfig<ExampleTeacher> {

    protected DefaultListModel<ExampleTeacher> teacherList;
    protected DefaultListModel<ExampleQualification> qualificationList;

    private List<ObjectTableColumn<ExampleTeacher>> objectColumnMap = new ArrayList<>();

    public ExampleTeacherTableConfiguration(DefaultListModel<ExampleTeacher> teacherList,
            DefaultListModel<ExampleQualification> qualificationList) {

        this.teacherList = teacherList;
        this.qualificationList = qualificationList;

        initialiseObjectColumnMap();
    }

    @Override
    public DefaultListModel<ExampleTeacher> getListModel() {
        return teacherList;
    }

    @Override
    final public ExampleTeacher createDefaultElement() {
        return new ExampleTeacher("", true, 0);
    }

    @Override
    public List<ObjectTableColumn<ExampleTeacher>> getObjectColumnMap() {
        return objectColumnMap;
    }

    private void initialiseObjectColumnMap() {
        ObjectTableColumn<ExampleTeacher> nameColumn =
                new ObjectTableColumnBuilder<ExampleTeacher>().setTitle("Name")
                        .setClass(String.class).setRowElementGetter((teacher) -> teacher.getName())
                        .setRowElementSetter((teacher, value) -> teacher.setName((String) value))
                        .build();

        ObjectTableColumn<ExampleTeacher> ageColumn = new ObjectTableColumnBuilder<ExampleTeacher>()
                .setTitle("Age").setClass(Integer.class)
                .setRowElementGetter((teacher) -> teacher.getAge())
                .setRowElementSetter((teacher, value) -> teacher.setAge((Integer) value)).build();

        ObjectTableColumn<ExampleTeacher> isCoolColumn =
                new ObjectTableColumnBuilder<ExampleTeacher>().setTitle("Is Cool?")
                        .setClass(Boolean.class).setRowElementGetter((teacher) -> teacher.isCool())
                        .setRowElementSetter((teacher, value) -> teacher.setCool((Boolean) value))
                        .build();

        /**
         * Create Cell Editor for Final Column
         */

        // CREATE A FILTER FUNCTION FOR OUR LIST SELECTOR
        BiFunction<List<ExampleQualification>, ExampleTeacher, List<ExampleQualification>> filterFunction =
                (qualificationList, teacherElement) -> {

                    Iterator<ExampleQualification> iterator = qualificationList.iterator();
                    while (iterator.hasNext()) {
                        ExampleQualification qualification = iterator.next();
                        String qFirstLetter = qualification.getName().toLowerCase().substring(0, 1);
                        String tFirstLetter =
                                teacherElement.getName().toLowerCase().substring(0, 1);

                        if (!tFirstLetter.equals(qFirstLetter)) {
                            iterator.remove();
                        }
                    }
                    return qualificationList;
                };

        // CREATE A LIST SELECTOR THAT IS USED ON THE QUALIFICATION COLUMN
        ObjectTableListSelector<ExampleTeacher, ExampleQualification> qualificationListSelector =
                new ObjectTableListSelector<>(qualificationList, teacherList,
                        "Choose Qualifications:", filterFunction);

        // Final Column
        ObjectTableColumn<ExampleTeacher> qualificationsColumn =
                new ObjectTableColumnBuilder<ExampleTeacher>().setTitle("Qualifications Held")
                        .setClass(List.class)
                        .setRowElementGetter((teacher) -> teacher.getQualifications())
                        .setRowElementSetter((teacher, value) -> teacher
                                .setQualifications((List<ExampleQualification>) value))
                        // This is where we add the list selector created above
                        .setCellEditor(qualificationListSelector).build();

        objectColumnMap.add(nameColumn);
        objectColumnMap.add(ageColumn);
        objectColumnMap.add(isCoolColumn);
        objectColumnMap.add(qualificationsColumn);
    }
}
