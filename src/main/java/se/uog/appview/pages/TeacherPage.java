package se.uog.appview.pages;

import se.uog.model.Teacher;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class TeacherPage extends PageView {
    public TeacherPage(ObjectTableModel<Teacher> tableModel) {
        JObjectTable<Teacher> table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public String getName() {
        return "Teachers";
    }
}
