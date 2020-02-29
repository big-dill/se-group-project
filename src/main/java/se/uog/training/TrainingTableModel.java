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

    private DefaultListModel<Training> trainingList;
    private DefaultListModel<Qualification> qualificationList;

    public TrainingTableModel(DefaultListModel<Training> t, DefaultListModel<Qualification> q) {
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

        ObjectTableColumn<Training> nameColumn = new ObjectTableColumnBuilder<Training>()
            .setTitle("Name")
            .setClass(String.class)
            .setRowElementGetter(training -> training.getName())
            .setRowElementSetter((training, val) -> training.setName((String)val))
            .setEditable(true)
            .build();

        
            
        ObjectTableColumn<Training> qualificationColumn = new ObjectTableColumnBuilder<Training>()
        .setTitle("Qualification")
        .setClass(Qualification.class)
        .setRowElementGetter(training -> training.getTrainingQualification())
        .setRowElementSetter((training, val) -> {
            List<Qualification> qualificationList = (List<Qualification>) val;
            training.setTrainingQualification(qualificationList.get(0));
        })
        .setCellEditor(new ObjectTableListSelector<Teacher, Qualification>(
             qualificationList, "Select Qualification"))
        .build();

        columns.add(nameColumn);
        columns.add(qualificationColumn);

        return columns;
    }






}