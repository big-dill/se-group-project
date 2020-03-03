package se.uog.table;

import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.swing.table.TableCellEditor;

/**
 * This class is consumed by {@link se.uog.table.ObjectTableModelAdaptor} and
 * {@link se.uog.table.ObjectTable}. It tells the model and view how to map each table column to the
 * underlying class's attribute, including it's getters and setters.
 * <p>
 * Getters are protected as they are only consumed by this package's classes.
 *
 * @param <E> The object which the table is mapped to.
 */
public class ObjectTableColumn<E> {

    private String columnTitle;
    private Class<?> columnClass;
    private BiConsumer<E, Object> rowElementSetter;
    private Function<E, Object> rowElementGetter;
    private boolean isColumnEditable;
    private TableCellEditor columnCellEditor;

    /**
     * An object which maps an element's attribute to an ObjectTable column.
     *
     * @param columnTitle      The title to display above the column.
     * @param columnClass      The class type the column deals with. This allows automatic setting
     *                         of renderers and editors, for example, checkboxes for Booleans.
     * @param rowElementSetter A function which calls the appropriate setter for the column. The
     *                         function is passed the element refererred to by the row (E) and the
     *                         value from the TableCellEditor (Object). This should be casted
     *                         appropriately.
     * @param rowElementGetter A function which calls the appropriate getter for the column. The
     *                         function is passed the element refererred to by the row (E) and is
     *                         expected to return an Object.
     * @param isColumnEditable If this is true, the column cells can be edited by the user using the
     *                         column's TableCellEditor.
     * @param columnCellEditor The TableCellEditor used to edit cells in this column. If this is
     *                         null, swing will set the default TableCellEditor for the provided
     *                         columnClass.
     */
    public ObjectTableColumn(String columnTitle, Class<?> columnClass,
            BiConsumer<E, Object> rowElementSetter, Function<E, Object> rowElementGetter,
            boolean isColumnEditable, TableCellEditor columnCellEditor) {

        this.columnTitle = columnTitle;
        this.columnClass = columnClass;
        this.rowElementSetter = rowElementSetter;
        this.rowElementGetter = rowElementGetter;
        this.isColumnEditable = isColumnEditable;
        this.columnCellEditor = columnCellEditor;
    }

    /**
     * Gets the column's title
     *
     * @return the column's title
     */
    public String getColumnTitle() {
        return columnTitle;
    }

    /**
     * Gets the class of the attribute which the column references.
     *
     * @return the class that the column deals with
     */
    public Class<?> getColumnClass() {
        return columnClass;
    }

    /**
     * Returns the setter used by the TableColumnEditor for this column.
     *
     * @return The setter function in the format:
     *         <p>
     *         {@code (element, value) -> {}}
     */
    public BiConsumer<E, Object> getRowElementSetter() {
        return rowElementSetter;
    }

    /**
     * Returns the getter used by the TableModel to tell it how to get the underlying element's
     * attribute.
     *
     * @return The getter function, in the format:
     *         <p>
     *         {@code (element) -> {return (Object)value;}}
     */
    public Function<E, Object> getRowElementGetter() {
        return rowElementGetter;
    }

    /**
     * Returns if the column can be edited by the JTable
     *
     * @return true if the cell is editable, false if not
     */
    public boolean isColumnEditable() {
        return isColumnEditable;
    }

    /**
     * Sets if the cell is editable by the user when in a JTable.
     *
     * @param isColumnEditable true if the cell is editable, false if not
     */
    public void setColumnEditable(boolean isColumnEditable) {
        this.isColumnEditable = isColumnEditable;
    }

    /**
     * Returns the TableCellEditor type used to edit cells in this column.
     *
     * @return the TableCellEditor for this column
     */
    public TableCellEditor getColumnCellEditor() {
        return columnCellEditor;
    }

}
