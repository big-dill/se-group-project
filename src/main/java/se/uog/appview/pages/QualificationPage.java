package se.uog.appview.pages;

import se.uog.model.Qualification;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class QualificationPage extends PageView {

    public QualificationPage(ObjectTableModel<Qualification> tableModel) {
        JObjectTable<Qualification> table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public String getName() {
        return "Qualifications";
    }
}
