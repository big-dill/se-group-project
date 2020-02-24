package se.uog.appview.pages;

import java.util.UUID;
import javax.swing.JPanel;
import se.uog.model.Teacher;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class TeacherPage extends PageView {
    public TeacherPage(ObjectTableConfig<Teacher> tableModel) {
        super();
        JObjectTable<Teacher> table = new JObjectTable<>(tableModel);
        add(table);
    }
}
