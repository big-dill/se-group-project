package se.uog.appview.pages;

import se.uog.model.Course;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

public class CoursePage extends PageView {

    public CoursePage(ObjectTableModel<Course> tableModel) {
        JObjectTable<Course> table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public String getName() {
        return "Courses";
    }

}
