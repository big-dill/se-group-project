package se.uog.swing.table;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Hello world!
 */
class TablePanel extends JPanel {

    private JTable table = new JTable();

    private JPanel buttonPanel = new JPanel();
    private JButton addButton;
    private JButton removeButton;

    public TablePanel(TableModel<?> tableModel) {

        table.setModel(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(400, 200));
        add(scrollpane, BorderLayout.CENTER);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = tableModel.addDefaultRow();
                table.changeSelection(rowIndex, 0, false, false);
                table.editCellAt(rowIndex, 0);
                table.transferFocus();
            }
        });

        removeButton = new JButton("Delete");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int rowIndex : selectedRows) {
                    tableModel.removeRow(rowIndex);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }

}
