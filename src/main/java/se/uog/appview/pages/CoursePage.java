package se.uog.appview.pages;

import javax.swing.JPanel;
import se.uog.model.Course;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class CoursePage extends JPanel {
    public CoursePage(ObjectTableConfig<Course> tableModel) {
        JObjectTable<Course> table = new JObjectTable<>(tableModel);
        add(table);
    }
}
