package se.uog.course;

import javax.swing.JPanel;

import se.uog.application.AppController;
import se.uog.application.TablePageView;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@SuppressWarnings("serial")
public class CoursePage extends JPanel implements TablePageView<Course> {

    private JObjectTable<Course> table;

    public CoursePage(ObjectTableModel<Course> tableModel, AppController appController) {
        table = new JObjectTable<>(tableModel);
        appController.addPropertyChangeListener(appController);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Course> model) {
        table.setModel(model);
    }

    @Override
    public void setTableEnabled(boolean isEnabled) {
        table.setEditable(isEnabled);

    }

    @Override
    public void setTableButtonsEnabled(boolean isEnabled) {
        table.setAddRemoveButtons(isEnabled);

    }

}
