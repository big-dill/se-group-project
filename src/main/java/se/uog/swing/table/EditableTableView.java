package se.uog.swing.table;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;


/**
 * Hello world!
 */
public class EditableTableView extends JPanel {

    private TableView tableView;

    private JPanel buttonPanel = new JPanel();
    private JButton addButton;
    private JButton removeButton;

    public EditableTableView(TableModel<?> tableModel) {

        tableView = new TableView(tableModel);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableModel.addDefaultRow();
                JTable table = tableView.getTable();
                table.changeSelection(rowIndex, 0, false, false);
                table.editCellAt(rowIndex, 0);
                table.transferFocus();
            }
        });

        removeButton = new JButton("Delete");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = tableView.getTable().getSelectedRows();
                for (int rowIndex : selectedRows) {
                    tableModel.removeRow(rowIndex);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(tableView, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public JTable getTable() {
        return tableView.getTable();
    }
}
