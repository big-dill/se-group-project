package se.uog.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import se.uog.table.ObjectTableColumn;
import se.uog.table.ObjectTableColumnBuilder;
import se.uog.table.ObjectTableConfig;

public class QualificationTableModel implements ObjectTableConfig<Qualification> {

    DefaultListModel<Qualification> qualificationList;

    public QualificationTableModel(DefaultListModel<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @Override
    public DefaultListModel<Qualification> getListModel() {
        return qualificationList;
    }

    @Override
    public se.uog.model.Qualification createDefaultElement() {
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