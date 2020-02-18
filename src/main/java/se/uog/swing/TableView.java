package se.uog.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class TableView extends JPanel {

    private JTable table = new JTable();

    public TableView(TableModel model, TableController controller) {

        // Attach model to table view
        table.setModel(model);

        table.setRowHeight(25);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(400, 200));
        add(scrollpane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        // Add Button - Calls controller to handle method
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.addRow();
            }
        });
        buttonPanel.add(addButton);

        // Remove Button - Calls controller to handle method
        JButton removeButton = new JButton("Delete Selected");
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int rowIndex : selectedRows) {
                    controller.removeRow(rowIndex);
                }
            }
        });
        buttonPanel.add(removeButton);


        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }
}
