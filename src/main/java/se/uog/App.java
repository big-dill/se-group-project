package se.uog;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import se.uog.swing.AppModel;
import se.uog.table.ObjectTable;

/**
 * Hello world!
 */
public class App extends JFrame {

    public App() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                AppModel model = new AppModel();

                ObjectTable teacherTableView = new ObjectTable(model.getTeacherTableModel());

                ObjectTable requirementTableView =
                        new ObjectTable(model.getRequirementTableModel());

                // requirementTableView.getTable().getColumnModel().getColumn(1)
                // .setCellEditor(new TableListEditor<Requirement, Qualification>(
                // model.getRequirementListModel(), model.getTeacherListModel(),
                // "Select Teachers:", (model, rowElement) -> {
                // // List<Qualification> qualifications =
                // // rowElement.getQualifications();
                // // model.getTeacherListModel() // Some kind of filter operation
                // // in here...
                // }));

                new App().add(requirementTableView);

                new App().add(teacherTableView);
            }
        });
    }

}
