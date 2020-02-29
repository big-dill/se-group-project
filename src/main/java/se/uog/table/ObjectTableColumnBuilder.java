package se.uog.table;

import javax.swing.table.TableCellEditor;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * A builder for ObjectTableColumn.
 *
 * Follows the Builder creation pattern to apply defaults and a more convenient / less error prone
 * way of creating an {@link se.uog.table.ObjectTableColumn}
 *
 * @param <E> The Class which the ObjectTable represents.
 */
public final class ObjectTableColumnBuilder<E> {

    // Column instance variables
    private String columnTitle;
    private Class<?> columnClass;
    private Function<E, Object> rowElementGetter;

    // With default values
    private boolean isColumnEditable = true;
    private TableCellEditor columnCellEditor = null;
    private BiConsumer<E, Object> rowElementSetter = (element, value) -> {
    };

    /**
     * @param columnTitle The title to display above the column.
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setTitle(String columnTitle) {
        this.columnTitle = columnTitle;
        return this;
    }

    /**
     * @param columnClass The class type the column deals with. This allows automatic setting of
     *                    renderers and editors, for example, checkboxes for Booleans.
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setClass(Class<?> columnClass) {
        this.columnClass = columnClass;
        return this;
    }

    /**
     * @param rowElementSetter A function which calls the appropriate setter for the column. The
     *                         function is passed the element refererred to by the row (E) and the
     *                         value from the TableCellEditor (Object). This should be casted
     *                         appropriately.
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setRowElementSetter(BiConsumer<E, Object> rowElementSetter) {
        this.rowElementSetter = rowElementSetter;
        return this;
    }

    /**
     * @param rowElementGetter A function which calls the appropriate getter for the column. The
     *                         function is passed the element refererred to by the row (E) and is
     *                         expected to return an Object.
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setRowElementGetter(Function<E, Object> rowElementGetter) {
        this.rowElementGetter = rowElementGetter;
        return this;
    }

    /**
     * @param isColumnEditable If this is true, the column cells can be edited by the user using the
     *                         column's TableCellEditor.
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setEditable(boolean isColumnEditable) {
        this.isColumnEditable = isColumnEditable;
        return this;
    }

    /**
     * @param columnCellEditor The TableCellEditor used to edit cells in this column. If this is
     *                         null, swing will set the default TableCellEditor for the provided
     *                         columnClass.
     *
     * @return this Builder object
     */
    public ObjectTableColumnBuilder<E> setCellEditor(TableCellEditor columnCellEditor) {
        this.columnCellEditor = columnCellEditor;
        return this;
    }

    /**
     * Builds the object created.
     *
     * @return The {@link se.uog.table.ObjectTableColumn} object to be built.
     * @throws IllegalStateException if a required field has not been set
     */
    public ObjectTableColumn<E> build() {

        if (columnTitle == null)
            throw new IllegalStateException("No column title");
        if (columnClass == null)
            throw new IllegalStateException("No column class");
        if (rowElementGetter == null)
            throw new IllegalStateException("No element getter");
        if (rowElementSetter == null && isColumnEditable)
            throw new IllegalStateException("No element setter for editable component");

        return new ObjectTableColumn<E>(columnTitle, columnClass, rowElementSetter,
                rowElementGetter, isColumnEditable, columnCellEditor);
    }
}
