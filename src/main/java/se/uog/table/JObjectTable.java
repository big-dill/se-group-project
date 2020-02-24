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
import javax.swing.table.TableColumnModel;

/**
 * A class that encapsulates an ObjectTable component. It was designed to allow the class to be
 * consumed in a similar fashion to other JComponents from Swing. Its architecture is similar to the
 * 'seperable model' architecture of Swing, only the difference is that it requires a more involved
 * configuration object because of how it 'maps' each table row and column to an object list element
 * and its attributes (respectively).
 *
 * @see se.uog.table.ObjectTableConfiguration
 *
 * @param <E> The class of object which the table represents.
 */
public class JObjectTable<E> extends JPanel {
    /**
     * Default serial version
     */
    private static final long serialVersionUID = 1L;

    // Model references:
    private ObjectTableModel<E> objectTableConfiguration;
    private DefaultListModel<E> listModel;
    private List<ObjectTableColumn<E>> objectColumnMap;

    // View references:
    // Table
    private JTable table = new JTable();
    // Button Panel
    private JPanel buttonPanel;

    /**
     * Constructs a table component which is mapped to the DefaultListModel provided in the
     * objectTableConfiguration. Similar to a JTable component, with added functionality. Models can
     * be swapped in and out using the {@link #setConfiguration()} method.
     *
     * @param objectTableConfiguration Provides the DefaultListModel<E> to map to, as well as the
     *                                 column configuration to tell the table how to display and
     *                                 edit each row's object's underlying attributes.
     */
    public JObjectTable(ObjectTableModel<E> objectTableConfiguration) {

        this.objectTableConfiguration = objectTableConfiguration;

        // Updates the table model, etc.
        setConfiguration(objectTableConfiguration);

        // View Setup
        // See private methods below.
        setupTable();
        setupButtonPanel();
    }

    /**
     * Sets the objectTableConfiguration for this component. Essentially, the configuration can be
     * viewed as a model for the JTable, albeit with a slightly more complicated setup also
     * included.
     *
     * @param objectTableConfiguration The configuration for this table.
     */
    public void setConfiguration(ObjectTableModel<E> objectTableConfiguration) {

        listModel = objectTableConfiguration.getListModel();
        objectColumnMap = objectTableConfiguration.getObjectColumnMap();

        // Create a JTable model using the object list and the column/attribute mapping in the
        // config

        // Link the view with this model
        table.setModel(new ObjectTableModelAdaptor<E>(listModel, objectColumnMap));

        // See private method below
        setTableCellEditors();
    }

    /**
     * Sets if the table is editable. If false, no cells can be edited and the Add/Delete button
     * panel is hidden.
     *
     * @param isEditable
     */
    public void setEditable(boolean isEditable) {
        buttonPanel.setVisible(isEditable);

        // Enable or disable all editors on the table.
        for (ObjectTableColumn<E> modelColumn : objectColumnMap) {
            modelColumn.setColumnEditable(isEditable);
        }

    }

    /**
     * Checks to see if a custom editor has been defined for a table column (for example, does the
     * objectColumnMap define if a combobox should be used to select an item, or is there a
     * listSelector which references another list, etc.).
     *
     * If a custom column editor is defined in the configuration, the JTable column model is updated
     * here.
     */
    private void setTableCellEditors() {

        TableColumnModel columnModel = table.getColumnModel();

        for (int i = 0; i < objectColumnMap.size(); i++) {
            // Get the custom TableCellEditor from the config object.
            TableCellEditor tableCellEditor = objectColumnMap.get(i).getColumnCellEditor();
            if (tableCellEditor != null) {
                // If there is a custom editor given, set it here.
                columnModel.getColumn(i).setCellEditor(tableCellEditor);
            }
        }
    }

    private void setupTable() {

        // LAYOUT
        table.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(400, 200));

        // ADD
        add(tableScrollPane, BorderLayout.CENTER);
    }

    /**
     * Much like in Swing architecture, the input 'controller' is defined within this component as
     * it is tightly coupled with this component's functionality. Thus the use of anonymous classes
     * within the ActionListeners.
     */
    private void setupButtonPanel() {
        JButton addButton = new JButton("Add");
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

        JButton removeButton = new JButton("Delete");
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
}
