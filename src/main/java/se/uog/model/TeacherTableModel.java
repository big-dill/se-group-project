package se.uog.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import se.uog.table.*;

public class TeacherTableModel implements ObjectTableModel<Teacher> {

    DefaultListModel<Teacher> teacherList;
    DefaultListModel<Qualification> qualificationList;

    public TeacherTableModel(DefaultListModel<Teacher> teacherList,
            DefaultListModel<Qualification> qualificationList) {

        this.teacherList = teacherList;
        this.qualificationList = qualificationList;
    }

    @Override
    public DefaultListModel<Teacher> getListModel() {
        return teacherList;
    }

    @Override
    public Teacher createDefaultElement() {
        return new Teacher("<< ANON >>");
    }

    @Override
    public List<ObjectTableColumn<Teacher>> getObjectColumnMap() {
        List<ObjectTableColumn<Teacher>> columns = new ArrayList<>();

        ObjectTableColumn<Teacher> nameColumn = new ObjectTableColumnBuilder<Teacher>()
                .setTitle("Name").setClass(String.class)
                .setRowElementGetter(teacher -> teacher.getName())
                .setRowElementSetter((teacher, val) -> teacher.setName((String) val)).build();

        ObjectTableColumn<Teacher> qualificationsColumn = new ObjectTableColumnBuilder<Teacher>()
                .setTitle("Qualifications").setClass(List.class)
                .setRowElementGetter(teacher -> teacher.getQualifications())
                .setRowElementSetter((teacher, val) -> {
                    // Set the qualifications here
                    teacher.clearQualifications();
                    for (Qualification q : (List<Qualification>) val) {
                        teacher.addQualification(q);
                    }
                }).setCellEditor(new ObjectTableListSelector<Teacher, Qualification>(
                        qualificationList, "Select Qualifications"))
                .build();

        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        return columns;
    }
}
