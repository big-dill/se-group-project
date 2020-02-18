package se.uog;

<<<<<<< HEAD
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
=======
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import se.uog.swing.Requirement;
import se.uog.swing.RequirementTableModel;
>>>>>>> parent of 3c32ae9... Add adder and remover

/**
 * Hello world!
 */
public class App extends JFrame {

    public App() {
        super("Application View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

        List<Teacher> listTeacher = new ArrayList<Teacher>();

        Teacher t1 = new Teacher("Julia");
        t1.addQualification("Maths");
        t1.addQualification("Science");

        Teacher t2 = new Teacher("Mark");
        t2.addQualification("Art");

<<<<<<< HEAD
        listTeacher.add(t1);
        listTeacher.add(t2);

        List<Requirement> listRequirement = new ArrayList<Requirement>();
        listRequirement.add(new Requirement("pizza", t1, true));
        listRequirement.add(new Requirement("spaghetti", t2, false));
        listRequirement.add(new Requirement("pasta", t2, false));

        Requirement[] requirements = listRequirement.toArray(new Requirement[listRequirement.size()]);
        Teacher[] teachers = listTeacher.toArray(new Teacher[listTeacher.size()]);

        DefaultComboBoxModel<Teacher> teacherModel =
            new DefaultComboBoxModel<Teacher>(teachers);

        DefaultComboBoxModel<Requirement> requirementModel =
            new DefaultComboBoxModel<Requirement>(requirements);

        RequirementTableModel requirementModel = new RequirementTableModel(listRequirement);
        RequirementTableView requirementView = new RequirementTableView(requirementModel, listTeacher);

        App mainWindow = new App();
        mainWindow.add()
=======
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
>>>>>>> parent of 3c32ae9... Add adder and remover

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new App();
            }
        });
    }

}
