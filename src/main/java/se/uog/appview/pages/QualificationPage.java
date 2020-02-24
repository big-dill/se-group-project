package se.uog.appview.pages;

import java.util.UUID;
import javax.swing.JPanel;
import se.uog.model.Qualification;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class QualificationPage extends PageView {

    public QualificationPage(ObjectTableConfig<Qualification> tableModel) {
        super();
        JObjectTable<Qualification> table = new JObjectTable<>(tableModel);
        add(table);
    }
}
