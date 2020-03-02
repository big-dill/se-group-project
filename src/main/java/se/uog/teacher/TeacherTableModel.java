package se.uog.teacher;

import se.uog.qualification.Qualification;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;
import se.uog.table.ObjectTableModel;
import se.uog.training.Training;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherTableModel implements ObjectTableModel<Teacher> {

    private List<ObjectTableColumn<Teacher>> columns = new ArrayList<>();
    private DefaultListModel<Teacher> teacherList;

    public TeacherTableModel(DefaultListModel<Teacher> teacherList,
                             DefaultListModel<Qualification> qualificationList, DefaultListModel<Training> trainingList) {

        this.teacherList = teacherList;

        // Setup Columns

        ObjectTableColumn<Teacher> nameColumn = new ObjectTableColumnBuilder<Teacher>()
            .setTitle("Name")
            .setClass(String.class)
            .setRowElementGetter(teacher -> teacher.getName())
            .setRowElementSetter((teacher, val) -> teacher.setName((String) val)).build();

        @SuppressWarnings("unchecked")
        ObjectTableColumn<Teacher> qualificationsColumn = new ObjectTableColumnBuilder<Teacher>()
            .setTitle("Qualifications")
            .setClass(List.class)
            .setRowElementGetter(teacher -> teacher.getQualifications())
            .setRowElementSetter((teacher, val) -> {
                // Set the qualifications here
                teacher.clearQualifications();
                for (Qualification q : (List<Qualification>) val) {
                    teacher.addQualification(q);
                }
            })
            .setCellEditor(new ObjectTableListSelector<Teacher, Qualification>(
                qualificationList, "Select Qualifications"))
            .build();

        ObjectTableColumn<Teacher> addTrainingColumn = new ObjectTableColumnBuilder<Teacher>()
            .setTitle("Add Training")
            .setClass(List.class)
            .setRowElementGetter(teacher -> teacher.getTraining())
            .setRowElementSetter((teacher, val) -> {
                teacher.setTraining((List<Training>) val);
            })
            .setCellEditor(new ObjectTableListSelector<Teacher, Training>(
                trainingList, "Select Training"))
            .build();

        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(addTrainingColumn);
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
        return columns;
    }
}
