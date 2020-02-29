package se.uog.teacher;

import javax.swing.JPanel;

import se.uog.application.TablePageView;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class TeacherPage extends JPanel implements TablePageView<Teacher> {
    private JObjectTable<Teacher> table;

    public TeacherPage(ObjectTableModel<Teacher> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Teacher> model) {
        table.setModel(model);
    }

    @Override
    public void setTableEnabled(boolean isEnabled) {
        table.setEditable(isEnabled);
    }

    @Override
    public void setTableButtonsEnabled(boolean isEnabled) {
        table.setAddRemoveButtons(isEnabled);
    }

}
