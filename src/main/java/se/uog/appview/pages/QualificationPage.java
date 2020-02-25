package se.uog.appview.pages;

import se.uog.model.Qualification;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class QualificationPage extends PageView implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public QualificationPage(ObjectTableModel<Qualification> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public String getName() {
        return "Qualifications";
    }

    @Override
    public void setTableModel(ObjectTableModel<Qualification> model) {
        table.setModel(model);
    }

}
