package se.uog.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import se.uog.swing.ExampleQualification;

public class ExampleQualificationTableConfiguration
        implements ObjectTableConfiguration<ExampleQualification> {
    /**
     * Default SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    DefaultListModel<ExampleQualification> qualificationList;

    public ExampleQualificationTableConfiguration(
            DefaultListModel<ExampleQualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public DefaultListModel<ExampleQualification> getListModel() {
        return qualificationList;
    }

    // This is a factory method which provides a default element that is created by the table when
    // a new row is added to it.
    @Override
    final public ExampleQualification createDefaultElement() {
        return new ExampleQualification(""); // Zero years old?! This is probably a bad default
                                             // :P
    }

    // This is a factory method override which we need to implement to provide the abstract class
    // with a mapping to show how its columns deal with the underlying object type.
    @Override
    public List<ObjectTableColumn<ExampleQualification>> getObjectColumnMap() {

        List<ObjectTableColumn<ExampleQualification>> objectColumnMap = new ArrayList<>();

        // This is where we create a 'mapping' which our ObjectTable can use to know how each
        // column is supposed to handle the underlying 'ExampleQualification' object.
        ObjectTableColumn<ExampleQualification> nameColumn =
                new ObjectTableColumnBuilder<ExampleQualification>().setTitle("Qualification Name")
                        .setClass(String.class)
                        .setRowElementGetter((qualification) -> qualification.getName())
                        .setRowElementSetter(
                                (qualification, value) -> qualification.setName((String) value))
                        .build();


        objectColumnMap.add(nameColumn);

        return objectColumnMap;
    }
}
