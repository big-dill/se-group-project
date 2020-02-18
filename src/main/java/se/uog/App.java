package se.uog;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

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

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new App();
            }
        });
    }

}
