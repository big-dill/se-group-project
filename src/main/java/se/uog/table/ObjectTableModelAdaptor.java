package se.uog.table;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

/**
 * This class adapts an ObjectTableModel into a TableModel which can be used by
 * JTable.
 *
 * It allows a JTable to represent an object, where the rows are the objects
 * inside a list of class<E>, and the columns are different attributes of the
 * class <E>. Thus, a cell represents a particular object and an attribute on
 * that object, whose getters and setters are defined in the ObjectTableModel
 * (using functional programming style lambdas).
 *
 * This reduces rewriting lots of code for views and controllers for each
 * different base class we have.
 *
 * It inherits from Swing's AbstractTableModel, and also implements Swing's
 * ListDataListener, so it can listen to the underlying list and ensure the
 * table view is synchronised with it.
 *
 * @param <E> The class of object which the table represents.
 */
public class ObjectTableModelAdaptor<E> extends AbstractTableModel implements ListDataListener {
    /**
     * Add default serial
     */
    private static final long serialVersionUID = 1L;

    private DefaultListModel<E> listModel;
    private List<ObjectTableColumn<E>> objectColumnMap;

    /**
     * Creates an adaptor which can be consumed by JTable, converting an
     * ObjectTableModel into a swing compatable TableModel.
     *
     * @param objectTableModel The object table model.
     */
    public ObjectTableModelAdaptor(ObjectTableModel<E> objectTableModel) {

        this.listModel = objectTableModel.getListModel();
        this.objectColumnMap = objectTableModel.getObjectColumnMap();

        // Adds a listener to the underlying list model so the table will update when it
        // does.
        listModel.addListDataListener(this);
        // Initially update the view.
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
        // Swing likes to know the classes so it can create default editors / renderers
        // for base primitives. For example, a boolean is represented with a checkbox by
        // default.
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
        // If it is, the table will be able to use its TableCellEditors to set the
        // values
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
