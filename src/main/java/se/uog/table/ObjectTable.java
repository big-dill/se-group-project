package se.uog.table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Hello world!
 */
public class ObjectTable<E> extends JPanel {
    /**
     * Default serial version
     */
    private static final long serialVersionUID = 1L;

    private JTable table = new JTable();
    private JScrollPane tableScrollPane;

    private DefaultListModel<E> listModel;
    private ObjectTableModel<E> tableModel;
    private List<ObjectTableColumn<E>> objectColumnMap;

    private JPanel buttonPanel;
    private JButton addButton;
    private JButton removeButton;


    public ObjectTable(ObjectTableConfiguration<E> objectTableConfiguration) {

        setConfiguration(objectTableConfiguration);

        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the default element to the list model.
                listModel.addElement(objectTableConfiguration.createDefaultElement());
                // Focus to the default element in the table and automatically
                // set it to start editing, because the default will probably want
                // to change.
                int rowIndex = listModel.getSize() - 1;
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
                    // Delete the element from the model.
                    listModel.removeElementAt(rowIndex);
                }
            }
        });

        buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setConfiguration(ObjectTableConfiguration<E> objectTableConfiguration) {

        listModel = objectTableConfiguration.getListModel();

        // Get the object-column mapping from the subclass using a factory method.
        objectColumnMap = objectTableConfiguration.getObjectColumnMap();

        tableModel = new ObjectTableModel<E>(listModel, objectColumnMap);
        // Create a JTable using an ObjectTableModel which combines the list model and how the table
        // should map to the containing objects.
        table.setModel(tableModel);

        // Check to see if a custom editor has been defined for a table column (for example, does
        // the objectColumnMap define if a combobox should be used to select an item, or is there a
        // listSelector which references another list, etc.).

        for (int i = 0; i < objectColumnMap.size(); i++) {
            ObjectTableColumn<E> objectTableColumn = objectColumnMap.get(i);
            TableCellEditor tableCellEditor = objectTableColumn.getColumnCellEditor();
            if (tableCellEditor != null) {
                // If one has been defined, set the JTable column model to use it.
                // Note: The column model is part of the 'view', which is why this is not
                // encapsulated
                // in the ObjectTableModel...which would have been cleaner. Swing, eh? :P
                table.getColumnModel().getColumn(i).setCellEditor(tableCellEditor);
            }
        }

        // LAYOUT
        table.setRowHeight(25);
        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 200));

        // ADD
        add(tableScrollPane, BorderLayout.CENTER);
    }

    public void setEditable(boolean isEditable) {
        buttonPanel.setVisible(isEditable);

        // Enable or disable all editors on the table.
        for (ObjectTableColumn<E> modelColumn : objectColumnMap) {
            modelColumn.setColumnEditable(isEditable);
        }

        // Trigger rebuild of view to enable / disable editors.
        tableModel.fireTableStructureChanged();
    }

}
