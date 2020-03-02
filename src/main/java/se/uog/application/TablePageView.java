package se.uog.application;

import javax.swing.JPanel;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

/**
 * Creates a JPanel 'page' which has an editable object table, with optional add / remove buttons.
 *
 * @param <E> The type of object represented in the table
 */
@SuppressWarnings("serial")
public class TablePageView<E> extends JPanel {

    private JObjectTable<E> table;

    /**
     * Creates a TablePageView
     *
     * @param tableModel the table model
     */
    public TablePageView(ObjectTableModel<E> tableModel) {
        table = new JObjectTable<>(tableModel);
        add(table);
    }

    /**
     * Updates the table view with a new model
     *
     * @param model the table model
     */
    public void setTableModel(ObjectTableModel<E> model) {
        table.setModel(model);
    }

    /**
     * Sets if the table cells can be edited and the add/remove buttons are available to the user.
     *
     * @param isEnabled true allows for table editing, false disables editing.
     */
    public void setTableEnabled(boolean isEnabled) {
        table.setEditable(isEnabled);

    }

    /**
     * Sets if the user can add/remove objects to the underlying list model using buttons.
     *
     * @param isEnabled true shows the add/remove buttons, false disables them.
     */
    public void setTableButtonsEnabled(boolean isEnabled) {
        table.setAddRemoveButtons(isEnabled);

    }


}
