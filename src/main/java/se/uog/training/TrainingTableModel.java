package se.uog.training;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import se.uog.qualification.Qualification;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableListSelector;
import se.uog.table.ObjectTableModel;
import se.uog.teacher.Teacher;

public class TrainingTableModel implements ObjectTableModel<Training> {

    private List<ObjectTableColumn<Training>> columns = new ArrayList<>();
    private DefaultListModel<Training> trainingList;
    private DefaultListModel<Qualification> qualificationList;

    public TrainingTableModel(DefaultListModel<Training> t, DefaultListModel<Qualification> q) {
        trainingList = t;
        qualificationList = q;

        // Setup Columns
        ObjectTableColumn<Training> nameColumn = new ObjectTableColumnBuilder<Training>()
        .setTitle("Name")
        .setClass(String.class)
        .setRowElementGetter(training -> training.getName())
        .setRowElementSetter((training, val) -> training.setName((String)val))
        .setEditable(true)
        .build();

        @SuppressWarnings("unchecked")
        ObjectTableColumn<Training> qualificationColumn = new ObjectTableColumnBuilder<Training>()
        .setTitle("Qualification")
        .setClass(List.class)
        .setRowElementGetter(training -> training.getTrainingQualificationList())
        .setRowElementSetter((training, val) -> {
            training.setTrainingQualificationList((List<Qualification>) val);
        })
        .setCellEditor(new ObjectTableListSelector<Teacher, Qualification>(
            qualificationList, "Select Qualification"))
        .build();

        columns.add(nameColumn);
        columns.add(qualificationColumn);
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
        return columns;
    }






}