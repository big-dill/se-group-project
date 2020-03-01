package se.uog;

import javax.swing.JPanel;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class TablePageView<E> extends JPanel {

    private JObjectTable<E> table;

    public TablePageView(ObjectTableModel<E> tableModel) {
    public CoursePage(ObjectTableModel<Course> tableModel, AppController appController) {
        table = new JObjectTable<>(tableModel);
        appController.addPropertyChangeListener(appController);
        add(table);
    }

    public void setTableModel(ObjectTableModel<E> model) {
        table.setModel(model);
    }

    public void setTableEnabled(boolean isEnabled) {
        table.setEditable(isEnabled);

    }

    public void setTableButtonsEnabled(boolean isEnabled) {
        table.setAddRemoveButtons(isEnabled);

    }

}
