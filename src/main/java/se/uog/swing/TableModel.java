package se.uog.swing;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;


public class TableModel<E> extends AbstractTableModel implements ListDataListener {
    private static final long serialVersionUID = 1L;

    private DefaultListModel<E> listModel;
    private E defaultElement;
    private ArrayList<TableModelColumn<E>> listTableColumn = new ArrayList<>();

    public TableModel(DefaultListModel<E> listModel, E defaultElement) {
        this.listModel = listModel;

        // Add listener to the listModel which will update.
        listModel.addListDataListener(this);
    }

    // Add or remove columns for this model.

    public void addTableColumn(TableModelColumn<E> tableColumn) {
        listTableColumn.add(tableColumn);
        fireTableStructureChanged();
    }

    public void removeTableColumn(TableModelColumn<E> tableColumn) {
        listTableColumn.remove(tableColumn);
        fireTableStructureChanged();
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
        return listTableColumn.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Reference columnNames
        return listTableColumn.get(columnIndex).getColumnName();
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Return the class to swing, so it can generate appropriate controls for different
        // primitive types.
        return listTableColumn.get(columnIndex).getColumnClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // This gets the value from the model and puts it in the GUI. :O
        E element = listModel.get(rowIndex);
        return listTableColumn.get(columnIndex).getColumnGetter().apply(element);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // This is used to set the value of the model item from the GUI. Sweet.
        E element = listModel.get(rowIndex);
        listTableColumn.get(columnIndex).getColumnSetter().accept(element, value);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return listTableColumn.get(columnIndex).isEditable();
    }

    // Additional Model Methods

    public void addDefaultRow() {
        // Add the default element
        listModel.addElement(defaultElement);
    }

    public void removeRow(int rowIndex) {
        // Remove the requirement with the row index
        listModel.remove(rowIndex);
        // The listeners for this swing event are implemented below as THIS is a listDataListener
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
