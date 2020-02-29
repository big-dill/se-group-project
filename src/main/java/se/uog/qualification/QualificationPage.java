package se.uog.qualification;

import javax.swing.JPanel;

import se.uog.application.TablePageView;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class QualificationPage extends JPanel implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public QualificationPage(ObjectTableModel<Qualification> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
        table.setEditable(false);
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
