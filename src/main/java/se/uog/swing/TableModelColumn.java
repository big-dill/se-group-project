package se.uog.swing;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class TableModelColumn<E> {
    private String columnName = "";
    private Class<?> columnClass = Object.class;
    private boolean isEditable = true;
    private BiConsumer<E, Object> columnSetter = (E, Object) -> {
    };
    private Function<E, Object> columnGetter = (E) -> null;

    public TableModelColumn() {
    }

    public TableModelColumn(String columnName, Class<?> columnClass, boolean isEditable,
            BiConsumer<E, Object> columnSetter, Function<E, Object> columnGetter) {
        this.columnName = columnName;
        this.columnClass = columnClass;
        this.isEditable = isEditable;
        this.columnSetter = columnSetter;
        this.columnGetter = columnGetter;
    }

    public String getColumnName() {
        return columnName;
    }

    public Class<?> getColumnClass() {
        return columnClass;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public BiConsumer<E, Object> getColumnSetter() {
        return columnSetter;
    }

    public Function<E, Object> getColumnGetter() {
        return columnGetter;
    }
}
