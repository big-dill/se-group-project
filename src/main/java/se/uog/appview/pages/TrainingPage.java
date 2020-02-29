package se.uog.appview.pages;

import se.uog.controller.AppController;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;
import javax.swing.*;
import se.uog.model.Training;


@SuppressWarnings("serial")
public class TrainingPage extends JPanel implements TablePageView<Training> {
    private JObjectTable<Training> table;

    public TrainingPage(AppController appController) {
        table = new JObjectTable<>(appController.getAppModel().getTrainingTableModel());
//        CurrentUserListener cu = new CurrentUserListener(appModel, this);


        add(table);

    }

    @Override
    public void setTableModel(ObjectTableModel<Training> model) {
        table.setModel(model);
    }
}
