package se.uog.swing;

import java.util.ArrayList;
import java.util.List;

public class TableModelConfig<E> {

    public List<TableModelColumn<E>> columnList = new ArrayList<>();

    public void addColumn(TableModelColumn<E> column) {
        columnList.add(column);
    }

    public int getColumnCount() {
        return columnList.size();
    }

    public String getColumnTitle(int columnIndex) {
        return columnList.get(columnIndex).getColumnName();
    }

    public Class<?> getColumnClass(int columnIndex) {
        return columnList.get(columnIndex).getClass();
    }

    public


    public Object columnGetter(E element, int columnIndex);

    public void columnSetter(E element, Object value, int columnIndex);

    public boolean isColumnEditable(int columnIndex);

    public E getDefaultElement();
}
