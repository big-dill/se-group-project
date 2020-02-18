package se.uog.swing;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;

public abstract class TableModel<E> extends AbstractTableModel {

    protected DefaultComboBoxModel<E> comboBoxModel;
    private E defaultElement;

    public TableModel(DefaultComboBoxModel<E> comboBoxModel, E defaultElement) {
        this.comboBoxModel = comboBoxModel;
        this.defaultElement = defaultElement;
    }

    /**
     * This method tells the TabelModel how to extract data from the element depending on which
     * column is chosen.
     *
     * It follows the template pattern, as most of the logic is encapsulated in this class.
     *
     * @param element     The element in the model to be modified using the value.
     * @param columnIndex The column that was modified by the user.
     * @return
     */
    public abstract Object getColumnValue(E element, int columnIndex);

    /**
     * This method tells the TabelModel how to set the underlying ELEMENT type found at the
     * columnIndex with the value received from the view.
     *
     * @param value       The value received from the user input.
     * @param element     The element in the model to be modified using the value.
     * @param columnIndex The column that was modified by the user.
     * @return
     */
    public abstract void setColumnValue(Object value, E element, int columnIndex);

    @Override
    public abstract int getColumnCount();

    @Override
    public abstract String getColumnName(int columnIndex);

    @Override
    final public Object getValueAt(int rowIndex, int columnIndex) {
        // This gets the value from the model and puts it in the GUI. :O
        E element = comboBoxModel.getElementAt(rowIndex);
        return getColumnValue(element, columnIndex);

    }

    @Override
    final public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // This is used to set the value of the model item from the GUI. Sweet.
        E element = comboBoxModel.getElementAt(rowIndex);
        setColumnValue(value, element, columnIndex);

    }

    @Override
    final public int getRowCount() {
        // Return the size of the array list
        return comboBoxModel.getSize();
    }


    @Override
    final public Class<?> getColumnClass(int columnIndex) {
        // Return the class to swing, so it can generate appropriate controls for different
        // primitive types.
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public abstract boolean isCellEditable(int rowIndex, int columnIndex);


    final public void addDefaultRow() {
        // Add to our list
        comboBoxModel.addElement(defaultElement);
        // Set the index to be the 'last' row
        int rowIndex = getRowCount();
        // Update the view by letting it know we've added a table row.
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    final public void removeRow(int rowIndex) {
        // Remove the requirement with the row index
        comboBoxModel.removeElementAt(rowIndex);
        // Update the view by letting it know we've nuked a table row.
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
