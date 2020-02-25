package se.uog.appview.pages;

import se.uog.model.Course;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class CoursePage extends PageView implements TablePageView<Course> {

    private JObjectTable<Course> table;

    public CoursePage(ObjectTableModel<Course> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public String getName() {
        return "Courses";
    }

    @Override
    public void setTableModel(ObjectTableModel<Course> model) {
        table.setModel(model);
    }

}
