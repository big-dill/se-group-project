package se.uog;

import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import se.uog.model.AppModel;
import se.uog.model.ExampleQualification;
import se.uog.model.ExampleTeacher;
import se.uog.table.AnotherTeacherTableConfiguration;
import se.uog.table.ExampleQualificationTableConfiguration;
import se.uog.table.ExampleTeacherTableConfiguration;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableConfig;

/**
 * Hello world!
 */
public class App extends JFrame {

    boolean toggle = false;

    public App() {

        AppModel model = new AppModel();

        ObjectTableConfig<ExampleQualification> qualificationTableConfiguration =
                new ExampleQualificationTableConfiguration(model.getQualificationListModel());

        JObjectTable<ExampleQualification> qualificationTable =
                new JObjectTable<>(qualificationTableConfiguration);

        // Two configs... potentially two models...

        ObjectTableConfig<ExampleTeacher> teacherTableConfiguration =
                new ExampleTeacherTableConfiguration(model.getTeacherListModel(),
                        model.getQualificationListModel());

        ObjectTableConfig<ExampleTeacher> anotherTableConfiguration =
                new AnotherTeacherTableConfiguration(model.getTeacherListModel(),
                        model.getQualificationListModel());

        JObjectTable<ExampleTeacher> teacherTable = new JObjectTable<>(teacherTableConfiguration);

        JPanel panel = new JPanel(new FlowLayout());

        JButton button = new JButton("Toggle Teacher Model");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (toggle) {
                    teacherTable.setConfiguration(teacherTableConfiguration);
                    teacherTable.setEditable(toggle);
                } else {
                    teacherTable.setConfiguration(anotherTableConfiguration);
                    teacherTable.setEditable(toggle);
                }

                toggle = !toggle;
            }
        });

        panel.add(teacherTable);
        panel.add(qualificationTable);
        panel.add(button);

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
