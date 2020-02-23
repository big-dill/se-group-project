package se.uog.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import se.uog.model.ExampleQualification;

public class ExampleQualificationTableConfiguration
        implements ObjectTableConfiguration<ExampleQualification> {

    DefaultListModel<ExampleQualification> qualificationList;

    public ExampleQualificationTableConfiguration(
            DefaultListModel<ExampleQualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public DefaultListModel<ExampleQualification> getListModel() {
        return qualificationList;
    }

    @Override
    final public ExampleQualification createDefaultElement() {
        return new ExampleQualification("");
    }

    @Override
    public List<ObjectTableColumn<ExampleQualification>> getObjectColumnMap() {

        List<ObjectTableColumn<ExampleQualification>> objectColumnMap = new ArrayList<>();

        // This is where we create a 'mapping' which our ObjectTable can use to know how each
        // column is supposed to handle the underlying 'ExampleQualification' object.
        ObjectTableColumn<ExampleQualification> nameColumn =
            new ObjectTableColumnBuilder<ExampleQualification>()
                .setTitle("Qualification Name")
                .setClass(String.class)
                .setRowElementGetter((qualification) -> qualification.getName())
                .setRowElementSetter(
                            (qualification, value) -> qualification.setName((String) value))
                .build();


        objectColumnMap.add(nameColumn);

        return objectColumnMap;
    }
}
