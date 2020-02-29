package se.uog.qualification;

import se.uog.application.AppController;
import se.uog.application.TablePageView;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import javax.swing.*;

@SuppressWarnings("serial")
public class QualificationPage extends JPanel implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public QualificationPage(ObjectTableModel<Qualification> tableModel, AppController appController) {
        table = new JObjectTable<>(tableModel);
        appController.addPropertyChangeListener(appController);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Qualification> model) {
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
