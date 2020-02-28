package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.model.Course;
import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CoursePage extends JPanel implements TablePageView<Course> {

    private JObjectTable<Course> table;

    public CoursePage(ObjectTableModel<Course> tableModel) {
        table = new JObjectTable<>(tableModel);
        UserType ue = UserType.getInstance();


        UserType.getInstance().addPropertyChangeListener(propertyChangeEvent -> {
            System.out.println(ue.getUserEnum());
            if (propertyChangeEvent.getNewValue().equals(UserEnum.ADMINISTRATOR) ||
                propertyChangeEvent.getNewValue().equals(UserEnum.DIRECTOR)){
                table.setEditable(false);
            }
            else{table.setEditable(true);}
        });

        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Course> model) {
        table.setModel(model);
    }


}
