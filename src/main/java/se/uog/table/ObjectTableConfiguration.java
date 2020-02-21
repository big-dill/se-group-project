package se.uog.table;

import java.util.ArrayList;
import java.util.List;

public class ObjectTableConfiguration<E> {
    private E defaultElement;
    private List<ObjectTableColumn<E>> tableColumns = new ArrayList<>();

    public ObjectTableConfiguration(E defaultElement) {
        this.defaultElement = defaultElement;
    }

    public void addColumn(ObjectTableColumn<E> column) {
        tableColumns.add(column);
    }

    public ObjectTableColumn<E> getColumn(int columnIndex) {
        return tableColumns.get(columnIndex);
    }

    public int getColumnCount() {
        return tableColumns.size();
    }

    public E getDefaultElement() {
        return defaultElement;
    }
    // At present, cannot see need to *remove* column, as this is more of a config class...
    // It's expected to be dynamic...
}
