package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.controller.AppController;
import se.uog.model.Teacher;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

@SuppressWarnings("serial")
public class TeacherPage extends JPanel implements TablePageView<Teacher> {
    private JObjectTable<Teacher> table;

    public TeacherPage(AppController appController) {
        table = new JObjectTable<>(appController.getAppModel().getTeacherTableModel());

   //     CurrentUserListener currentUserListener = new CurrentUserListener(appModel, this);

    //    currentUserListener.addPropertyChangeListener(currentUserListener);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Teacher> model) {
        table.setModel(model);
    }
}
