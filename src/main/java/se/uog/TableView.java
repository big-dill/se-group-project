package se.uog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import se.uog.swing.AppModel;
import se.uog.swing.Requirement;
import se.uog.swing.TableModel;
import se.uog.swing.Teacher;

/**
 * Hello world!
 */
public class TableView extends JFrame {

    private JTable table = new JTable();

    public TableView(TableModel<?> tableModel) {

        table.setModel(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(400, 200));
        add(scrollpane, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.addDefaultRow();
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

                AppModel model = new AppModel();

                TableModel<Teacher> teacherModel = model.getTeacherTableModel();
                TableModel<Requirement> requirementModel = model.getRequirementTableModel();

                new TableView(requirementModel);

                new TableView(teacherModel);
            }
        });
    }

}
