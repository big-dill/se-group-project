package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.model.Qualification;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class QualificationPage extends JPanel implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public QualificationPage(ObjectTableModel<Qualification> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Qualification> model) {
        table.setModel(model);
    }

}
