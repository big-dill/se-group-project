package se.uog.table;

import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.swing.table.TableCellEditor;

public final class ObjectTableColumnBuilder<E> {

    // Column instance variables
    private String columnTitle;
    private Class<?> columnClass;
    private BiConsumer<E, Object> rowElementSetter;
    private Function<E, Object> rowElementGetter;

    // With default values
    private boolean isColumnEditable = true;
    private TableCellEditor columnCellEditor = null;


    public ObjectTableColumnBuilder<E> setTitle(String columnTitle) {
        this.columnTitle = columnTitle;
        return this;
    }


    public ObjectTableColumnBuilder<E> setClass(Class<?> columnClass) {
        this.columnClass = columnClass;
        return this;
    }


    public ObjectTableColumnBuilder<E> setRowElementSetter(BiConsumer<E, Object> rowElementSetter) {
        this.rowElementSetter = rowElementSetter;
        return this;
    }


    public ObjectTableColumnBuilder<E> setRowElementGetter(Function<E, Object> rowElementGetter) {
        this.rowElementGetter = rowElementGetter;
        return this;
    }


    public ObjectTableColumnBuilder<E> setEditable(boolean isColumnEditable) {
        this.isColumnEditable = isColumnEditable;
        return this;
    }

    public ObjectTableColumnBuilder<E> setCellEditor(TableCellEditor columnCellEditor) {
        this.columnCellEditor = columnCellEditor;
        return this;
    }

    public ObjectTableColumn<E> build() {

        if (columnTitle == null)
            throw new IllegalStateException("No column title");
        if (columnClass == null)
            throw new IllegalStateException("No column class");
        if (rowElementGetter == null)
            throw new IllegalStateException("No element getter");
        if (rowElementSetter == null)
            throw new IllegalStateException("No element setter");

        return new ObjectTableColumn<E>(columnTitle, columnClass, rowElementSetter,
                rowElementGetter, isColumnEditable, columnCellEditor);
    }
}
