package se.uog.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;
import se.uog.table.ObjectTableModel;

public class TrainingTableModel implements ObjectTableModel<Training> {
    private DefaultListModel<Teacher> teacherList;
    private DefaultListModel<Training> trainingList;
    private DefaultListModel<Qualification> qualificationList;

    TrainingTableModel(DefaultListModel<Training> t, DefaultListModel<Qualification> q, DefaultListModel<Teacher> tl) {
        teacherList = tl;
        trainingList = t;
        qualificationList = q;
    }

    @Override
    public DefaultListModel<Training> getListModel() {
        return trainingList;
    }
    
    @Override
    public Training createDefaultElement() {
        return new Training("");
    }

    @Override
    public List<ObjectTableColumn<Training>> getObjectColumnMap() {
        List<ObjectTableColumn<Training>> columns = new ArrayList<>();

        ObjectTableColumnBuilder<Training> trainingObjectTableColumnBuilder = new ObjectTableColumnBuilder<>();
        trainingObjectTableColumnBuilder.setTitle("Designated Teachers");
        trainingObjectTableColumnBuilder.setClass(List.class);
        trainingObjectTableColumnBuilder.setRowElementGetter(Training::getAssignedTeachers);
        trainingObjectTableColumnBuilder.setRowElementSetter((training, val) -> training.setAssignedTeachers((List<Teacher>) val));
        trainingObjectTableColumnBuilder.setCellEditor(new ObjectTableListSelector<Course, Teacher>(
            teacherList,
            "Select Teacher(s):"
        ));
        ObjectTableColumn<Training> teachersColumn = trainingObjectTableColumnBuilder
            .build();



        ObjectTableColumn<Training> trainingNameColumn = new ObjectTableColumnBuilder<Training>()
            .setTitle("Training")
            .setClass(String.class)
            .setRowElementGetter(Training::getName)
            .setRowElementSetter((training, val) -> training.setName((String)val))
            .setEditable(true)
            .build();

        
            
        ObjectTableColumn<Training> qualificationColumn = new ObjectTableColumnBuilder<Training>()
        .setTitle("Qualification")
        .setClass(Qualification.class)
        .setRowElementGetter(Training::getTrainingQualification)
        .setRowElementSetter((training, val) -> {
            List<Qualification> qualificationList = (List<Qualification>) val;
            training.setTrainingQualification(qualificationList.get(0));
        })
        .setCellEditor(new ObjectTableListSelector<Teacher, Qualification>(
             qualificationList, "Select Qualification"))
        .build();

        columns.add(teachersColumn);
        columns.add(trainingNameColumn);
        columns.add(qualificationColumn);

        return columns;
    }






}
