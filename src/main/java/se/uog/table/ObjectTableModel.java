package se.uog.table;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;


public class ObjectTableModel<E> extends AbstractTableModel implements ListDataListener {
    private static final long serialVersionUID = 1L;

    private DefaultListModel<E> listModel;
    private ObjectTableConfiguration<E> configuration;

    public ObjectTableModel(DefaultListModel<E> listModel,
            ObjectTableConfiguration<E> configuration) {

        this.listModel = listModel;
        this.configuration = configuration;

        // Add listener to the listModel which will update.
        listModel.addListDataListener(this);
        // fireTableStructureChanged();
    }

    // Abstract Table Model Overrides

    @Override
    public int getRowCount() {
        // Return the size of the array list
        return listModel.getSize();
    }

    @Override
    public int getColumnCount() {
        // Return length of the names
        return configuration.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Reference columnNames
        return configuration.getColumn(columnIndex).getColumnTitle();
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Return the class to swing, so it can generate appropriate controls for different
        // primitive types.
        Class<?> x = configuration.getColumn(columnIndex).getColumnClass();
        // Need to reimplement this so it sets the actual classes we want?
        return x;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // This gets the value from the model and puts it in the GUI. :O
        E element = listModel.get(rowIndex);
        return configuration.getColumn(columnIndex).getRowElementGetter().apply(element);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // This is used to set the value of the model item from the GUI. Sweet.
        E element = listModel.get(rowIndex);
        configuration.getColumn(columnIndex).getRowElementSetter().accept(element, value);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return configuration.getColumn(columnIndex).isColumnEditable();
    }

    // Additional Model Methods

    public int addRow(E element) {
        // Add to our list
        listModel.addElement(element);
        return listModel.getSize() - 1;
        // The listeners for this swing event are implemented below as THIS is a listDataListener
    }

    public int addDefaultRow() {
        // Add the default element
        return addRow(configuration.getDefaultElement());
    }

    public void removeRow(int rowIndex) {
        // Remove the requirement with the row index
        listModel.remove(rowIndex);
        // The listeners for this swing event are implemented below as THIS is a listDataListener
    }

    // Can change the headers, may be useful for changing the display depending on the current user
    public void setConfiguration(ObjectTableConfiguration<E> configuration) {
        this.configuration = configuration;
        fireTableStructureChanged();
    }

    // ListDataListener Overrides

    @Override
    public void intervalAdded(ListDataEvent e) {
        fireTableRowsInserted(e.getIndex0(), e.getIndex1());

    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        fireTableRowsDeleted(e.getIndex0(), e.getIndex1());

    }

    @Override
    public void contentsChanged(ListDataEvent e) {
        fireTableDataChanged();
    }

}
