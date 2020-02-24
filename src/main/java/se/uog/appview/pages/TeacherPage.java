package se.uog.appview.pages;

import javax.swing.JPanel;
import se.uog.model.Teacher;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class TeacherPage extends JPanel {
    public TeacherPage(ObjectTableConfig<Teacher> tableModel) {
        JObjectTable<Teacher> table = new JObjectTable<>(tableModel);
        add(table);
    }
}
