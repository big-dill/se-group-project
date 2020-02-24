package se.uog.appview.pages;

import java.util.UUID;
import javax.swing.JPanel;
import se.uog.model.Course;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

public class CoursePage extends PageView {

    public CoursePage(ObjectTableConfig<Course> tableModel) {
        super();
        JObjectTable<Course> table = new JObjectTable<>(tableModel);
        add(table);
    }

}
