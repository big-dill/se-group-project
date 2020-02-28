package se.uog.appview.pages;

import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;
import javax.swing.*;
import se.uog.model.Training;


@SuppressWarnings("serial")
public class TrainingPage extends JPanel implements TablePageView<Training> {
    private JObjectTable<Training> table;

    public TrainingPage(ObjectTableModel<Training> tableModel) {
        table = new JObjectTable<>(tableModel);
        UserType ue = UserType.getInstance();

        UserType.getInstance().addPropertyChangeListener(propertyChangeEvent -> {
            System.out.println(ue.getUserEnum());
            if (propertyChangeEvent.getNewValue().equals(UserEnum.CLASS_DIRECTOR)){
                table.setEditable(false);
            }
            else{table.setEditable(true);}
        });


        add(table);

    }

    @Override
    public void setTableModel(ObjectTableModel<Training> model) {
        table.setModel(model);
    }
}
