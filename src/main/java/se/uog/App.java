package se.uog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import se.uog.swing.Requirement;
import se.uog.swing.RequirementTableModel;

/**
 * Hello world!
 */
public class App extends JFrame {


    // Create a table which gets the teachers from a model...
    private JTable table = new JTable() {
        @Override
        public TableCellEditor getCellEditor(int row, int column) {
            if (column == RequirementTableModel.TEACHER_COLUMN) {
                String qualification =
                        (String) this.getValueAt(row, RequirementTableModel.QUALIFICATION_COLUMN);

                JComboBox<Teacher> dropdown = new JComboBox<Teacher>(teacherList);
                return new DefaultCellEditor(dropdown);
            }

            return super.getCellEditor(row, column);
        }
    };



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

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addRow(new Requirement("", "", false));
            }

        });

        JButton removeButton = new JButton("Delete Selected");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int rowIndex : selectedRows) {
                    tableModel.removeRow(rowIndex);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);


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
