package se.uog.qualification;

import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QualificationTableModel implements ObjectTableModel<Qualification> {

    private List<ObjectTableColumn<Qualification>> columns = new ArrayList<>();
    private DefaultListModel<Qualification> qualificationList;

    public QualificationTableModel(DefaultListModel<Qualification> qualificationList) {
        this.qualificationList = qualificationList;

        // Setup Columns

        ObjectTableColumn<Qualification> nameColumn = new ObjectTableColumnBuilder<Qualification>()
            .setTitle("Name")
            .setClass(String.class)
            .setRowElementGetter(qualification -> qualification.getQualificationName())
            .setRowElementSetter((qualification, val) -> qualification.setQualificationName((String)val))
            .setEditable(true)
            .build();

        columns.add(nameColumn);
    }

    @Override
    public DefaultListModel<Qualification> getListModel() {
        return qualificationList;
    }

    @Override
    public Qualification createDefaultElement() {
        return new Qualification("");
    }

    @Override
    public List<ObjectTableColumn<Qualification>> getObjectColumnMap() {
        return columns;
    }

}
