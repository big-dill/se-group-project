package se.uog;

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

/**
 * Hello world!
 */
public class App extends JFrame {

    private JTable table = new JTable();
    private RequirementTableModel tableModel;

    public App() {
        super("Displaying list editors in Swing");

        List<Requirement> listRequirement = new ArrayList<>();
        listRequirement.add(new Requirement("pizza", "Julia", true));
        listRequirement.add(new Requirement("spaghetti", "Hugh", false));
        listRequirement.add(new Requirement("pasta", "Mark", false));


        tableModel = new RequirementTableModel(listRequirement);
        table.setModel(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(400, 200));
        add(scrollpane, BorderLayout.CENTER);

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
