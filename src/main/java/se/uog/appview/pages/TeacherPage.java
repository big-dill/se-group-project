package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.model.Teacher;
import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class TeacherPage extends JPanel implements TablePageView<Teacher> {
    private JObjectTable<Teacher> table;

    public TeacherPage(ObjectTableModel<Teacher> tableModel) {
        table = new JObjectTable<>(tableModel);

        UserType ue = UserType.getInstance();

        UserType.getInstance().addPropertyChangeListener(propertyChangeEvent -> {
            System.out.println(ue.getUserEnum());
            if (propertyChangeEvent.getNewValue().equals(UserEnum.DIRECTOR)){
                table.setEditable(false);
            }
            else{table.setEditable(true);}
        });

        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Teacher> model) {
        table.setModel(model);
    }
}
