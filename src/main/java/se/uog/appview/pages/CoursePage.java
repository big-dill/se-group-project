package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.model.Course;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class CoursePage extends JPanel implements TablePageView<Course> {

    private JObjectTable<Course> table;

    public CoursePage(ObjectTableModel<Course> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Course> model) {
        table.setModel(model);
    }

}
