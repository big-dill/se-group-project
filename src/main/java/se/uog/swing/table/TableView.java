package se.uog.swing.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Hello world!
 */
public class TableView extends JPanel {

    private JTable table = new JTable();

    public TableView(TableModel<?> tableModel) {

        table.setModel(tableModel);
        table.setRowHeight(25);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(400, 200));
        add(scrollpane, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

}
