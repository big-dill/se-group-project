package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.model.Course;
import se.uog.model.UserEnum;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class CoursePage extends JPanel implements TablePageView<Course> {

    private JObjectTable<Course> table;

    public CoursePage(AppController appController) {
        table = new JObjectTable<>(appController.getAppModel().getCourseTableModel());
        UserEnum ue = appController.getAppModel().getCurrentUser();


        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Course> model) {
        table.setModel(model);
    }

    public JObjectTable<Course> getTable() {
        return table;
    }
}
