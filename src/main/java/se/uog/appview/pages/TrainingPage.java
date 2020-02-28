package se.uog.appview.pages;

import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import javax.swing.*;

import se.uog.model.Training;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TrainingPage extends JPanel implements TablePageView<Training> {
    private JObjectTable<Training> table;

    public TrainingPage (ObjectTableModel<Training> tableModel){
        table = new JObjectTable<>(tableModel);
        UserType ue = UserType.getInstance();
        JLabel jl = new JLabel(String.valueOf(ue.toString()));
        add(jl);

        add(table);



        UserType.getInstance().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                System.out.println(ue.getUserEnum());
                jl.setText(String.valueOf(ue));
            }
        });

    }

    @Override
    public void setTableModel(ObjectTableModel<Training> model) {
        table.setModel(model);
    }
}
