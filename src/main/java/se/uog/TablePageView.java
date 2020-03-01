package se.uog;

import se.uog.application.AppController;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import javax.swing.*;

@SuppressWarnings("serial")
public class TablePageView<E> extends JPanel {

    private JObjectTable<E> table;

    public TablePageView(ObjectTableModel<E> tableModel, AppController appController) {
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
