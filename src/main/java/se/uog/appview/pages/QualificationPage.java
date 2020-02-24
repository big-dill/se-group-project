package se.uog.appview.pages;

import javax.swing.JPanel;
import se.uog.model.Qualification;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class QualificationPage extends JPanel {
    public QualificationPage(ObjectTableConfig<Qualification> tableModel) {
        JObjectTable<Qualification> table = new JObjectTable<>(tableModel);
        add(table);
    }
}
