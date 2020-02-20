package se.uog;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import se.uog.swing.AppModel;
import se.uog.swing.Requirement;
import se.uog.swing.table.EditableTableView;
import se.uog.swing.table.TableListEditor;
import se.uog.swing.table.TableModel;

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

                EditableTableView teacherTableView =
                        new EditableTableView(model.getTeacherTableModel());

                EditableTableView requirementTableView =
                        new EditableTableView(model.getRequirementTableModel());

                requirementTableView.getTable().getColumnModel().getColumn(1).setCellEditor(
                        new TableListEditor(model.getTeacherListModel(), "Select Teachers:"));

                new App().add(requirementTableView);

                new App().add(teacherTableView);
            }
        });
    }

}
