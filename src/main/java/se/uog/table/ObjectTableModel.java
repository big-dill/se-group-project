package se.uog.table;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;


public class ObjectTableModel<E> extends AbstractTableModel implements ListDataListener {
    private static final long serialVersionUID = 1L;

    private DefaultListModel<E> listModel;
    private List<ObjectTableColumn<E>> objectColumnMap;

    public ObjectTableModel(DefaultListModel<E> listModel,
            List<ObjectTableColumn<E>> objectColumnMap) {

        this.listModel = listModel;
        this.objectColumnMap = objectColumnMap;

        // Adds a listener to the underlying list model so the table will update when it does.
        listModel.addListDataListener(this);
        fireTableStructureChanged();
    }

    // AbstractTableModel Overrides

    @Override
    public int getRowCount() {
        // The number of rows will be the same as the number of elements in the List<E>
        return listModel.getSize();
    }

    @Override
    public int getColumnCount() {
        // The columns are provided by the objectColumnMap.
        return objectColumnMap.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Gets the column title from the ObjectTableColumn object.
        return objectColumnMap.get(columnIndex).getColumnTitle();
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Gets the class of the column item from the ObjectTableColumn object.
        // ---
        // Swing likes to know the classes so it can create default editors / renderers for
        // base primitives. For example, a boolean is represented with a check box.
        return objectColumnMap.get(columnIndex).getColumnClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // We pass the element from the current row to the ObjectTableColumn object's
        // lambda 'getter' function. If <E> were a String, an example lambda be:
        // (element) -> (element.length()). This would tell the table to populate the
        // table with the string's length.
        E element = listModel.get(rowIndex);
        return objectColumnMap.get(columnIndex).getRowElementGetter().apply(element);
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // We pass the element from the current row to the ObjectTableColumn object's
        // lambda 'setter' function, along with the value that the TableCellEditor has
        // returned to us.
        // So, if <E> were a Person, an example lambda be:
        // (element) -> (element.setName((String) value))
        // This allows us to use the table to edit the underlying elements. Neat!
        // Note: we need to cast the value in the setter.
        E element = listModel.get(rowIndex);
        objectColumnMap.get(columnIndex).getRowElementSetter().accept(element, value);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Gets if the column item from the ObjectTableColumn object is editable.
        // If it is, the table will be able to use its TableCellEditors to set the values
        // of the objects inside the underlying listModel.
        return objectColumnMap.get(columnIndex).isColumnEditable();
    }

    // ListDataListener Overrides

    // These tell the model to update its view if anything changes in the underlying
    // list model to keep everything nice and synchronised.

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
