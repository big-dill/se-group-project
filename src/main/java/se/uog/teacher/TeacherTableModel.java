package se.uog.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import se.uog.qualification.Qualification;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;
import se.uog.table.ObjectTableModel;
import se.uog.training.Training;

public class TeacherTableModel implements ObjectTableModel<Teacher> {

    private DefaultListModel<Teacher> teacherList;
    private DefaultListModel<Qualification> qualificationList;
    private DefaultListModel<Training> trainingList;

    public TeacherTableModel(DefaultListModel<Teacher> teacherList,
            DefaultListModel<Qualification> qualificationList, DefaultListModel<Training> trainingList) {

        this.teacherList = teacherList;
        this.qualificationList = qualificationList;
        this.trainingList = trainingList;
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
                .setTitle("Name")
                .setClass(String.class)
                .setRowElementGetter(teacher -> teacher.getName())
                .setRowElementSetter((teacher, val) -> teacher.setName((String) val)).build();

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
        .setClass(Training.class)
        .setRowElementGetter(teacher -> teacher.getTraining())
        .setRowElementSetter((teacher, val) -> {
            List<Training> trainingList = (List<Training>) val;
            teacher.setTraining(trainingList.get(0));
        })
        .setCellEditor(new ObjectTableListSelector<Teacher, Training>(
                        trainingList, "Select Training"))
                .build();

        columns.add(nameColumn);
        columns.add(qualificationsColumn);
        columns.add(addTrainingColumn);
        return columns;
    }
}
