package se.uog.model;


import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableModel;

public class TrainingTableModel implements ObjectTableModel<Training> {

    private DefaultListModel<Training> trainingList;

    public TrainingTableModel(DefaultListModel<Training> t) {
        trainingList = t;
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

        columns.add(nameColumn);

        return columns;
    }






}