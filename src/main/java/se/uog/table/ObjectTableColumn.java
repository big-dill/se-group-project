package se.uog.table;

import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.swing.table.TableCellEditor;

public class ObjectTableColumn<E> {

    private String columnTitle;
    private Class<?> columnClass;
    private BiConsumer<E, Object> rowElementSetter;
    private Function<E, Object> rowElementGetter;
    private boolean isColumnEditable;
    private TableCellEditor columnCellEditor;

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

    public String getColumnTitle() {
        return columnTitle;
    }

    public Class<?> getColumnClass() {
        return columnClass;
    }

    public BiConsumer<E, Object> getRowElementSetter() {
        return rowElementSetter;
    }

    public Function<E, Object> getRowElementGetter() {
        return rowElementGetter;
    }

    public boolean isColumnEditable() {
        return isColumnEditable;
    }

    public void setColumnEditable(boolean isColumnEditable) {
        this.isColumnEditable = isColumnEditable;
    }

    public TableCellEditor getColumnCellEditor() {
        return columnCellEditor;
    }

}
