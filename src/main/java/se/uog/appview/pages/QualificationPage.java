package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.model.Qualification;
import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;


@SuppressWarnings("serial")
public class QualificationPage extends JPanel implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public QualificationPage(ObjectTableModel<Qualification> tableModel) {
        table = new JObjectTable<>(tableModel);
        UserType ue = UserType.getInstance();

        UserType.getInstance().addPropertyChangeListener(propertyChangeEvent -> {
            System.out.println(ue.getUserEnum());
            if (propertyChangeEvent.getNewValue().equals(UserEnum.DIRECTOR)
            || propertyChangeEvent.getNewValue().equals(UserEnum.ADMINISTRATOR)){
                table.setEditable(false);
            }
            else{table.setEditable(true);}
        });

        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Qualification> model) {
        table.setModel(model);
    }
}
