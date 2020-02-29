package se.uog.appview.pages;

import javax.swing.JPanel;

import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.model.Qualification;
import se.uog.model.UserEnum;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import java.beans.PropertyChangeSupport;


@SuppressWarnings("serial")
public class QualificationPage extends JPanel implements TablePageView<Qualification> {

    private JObjectTable<Qualification> table;

    public JObjectTable<Qualification> getTable() {
        return table;
    }

    public QualificationPage(AppController appController) {
        table = new JObjectTable<>(appController.getAppModel().getQualificationTableModel());
        PropertyChangeSupport ue = appController.getAppModel().getPropertyChangeSupport();
        ue.addPropertyChangeListener(appController);
        add(table);
    }

    @Override
    public void setTableModel(ObjectTableModel<Qualification> model) {
        table.setModel(model);
    }
}
