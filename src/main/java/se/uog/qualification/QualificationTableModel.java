package se.uog.qualification;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableModel;

public class QualificationTableModel implements ObjectTableModel<Qualification> {

    private DefaultListModel<Qualification> qualificationList;

    public QualificationTableModel(DefaultListModel<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
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
        List<ObjectTableColumn<Qualification>> columns = new ArrayList<>();

        ObjectTableColumn<Qualification> nameColumn = new ObjectTableColumnBuilder<Qualification>()
            .setTitle("Name")
            .setClass(String.class)
            .setRowElementGetter(qualification -> qualification.getQualificationName())
            .setRowElementSetter((qualification, val) -> qualification.setQualificationName((String)val))
            .setEditable(true)
            .build();

            

        columns.add(nameColumn);

        return columns;
    }

}