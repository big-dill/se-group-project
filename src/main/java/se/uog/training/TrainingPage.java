package se.uog.training;

import javax.swing.JPanel;

import se.uog.application.AppController;
import se.uog.application.TablePageView;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;


@SuppressWarnings("serial")
public class TrainingPage extends JPanel implements TablePageView<Training> {
    private JObjectTable<Training> table;

    public TrainingPage(ObjectTableModel<Training> tableModel, AppController appController) {
        table = new JObjectTable<>(tableModel);
        appController.addPropertyChangeListener(appController);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Training> model) {
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
