package se.uog.appview.pages;

import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import javax.swing.JPanel;

import se.uog.model.Training;

public class TrainingPage extends JPanel implements TablePageView<Training> {
    private JObjectTable<Training> table;

    public TrainingPage (ObjectTableModel<Training> tableModel){
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Training> model) {
        table.setModel(model);
    }
}