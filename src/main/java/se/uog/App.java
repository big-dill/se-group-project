package se.uog;

import javax.swing.*;
import java.awt.*;
import se.uog.swing.*;
import se.uog.table.*;

/**
 * Hello world!
 */
public class App extends JFrame {

    public App() {

        AppModel model = new AppModel();

        ObjectTableConfiguration<ExampleQualification> qualificationTableConfiguration =
                new ExampleQualificationTableConfiguration(model.getQualificationListModel());

        ObjectTable<ExampleQualification> qualificationTable =
                new ObjectTable<>(qualificationTableConfiguration);

        ObjectTableConfiguration<ExampleTeacher> teacherTableConfiguration =
                new ExampleTeacherTableConfiguration(model.getTeacherListModel(),
                        model.getQualificationListModel());

        ObjectTable<ExampleTeacher> teacherTable = new ObjectTable<>(teacherTableConfiguration);

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(teacherTable);
        panel.add(qualificationTable);

        teacherTable.setEditable(false);

        add(panel);

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
                new App();
            }
        });
    }

}
