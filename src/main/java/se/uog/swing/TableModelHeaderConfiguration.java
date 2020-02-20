package se.uog.swing;

public interface TableModelHeaderConfiguration<E> {
    public int getColumnCount();

    public String[] getColumnNames();

    public Class<?>[] getColumnClasses();

    public Object columnGetter(E element, int columnIndex);

    public void columnSetter(E element, Object value, int columnIndex);

    public boolean isColumnEditable(int columnIndex);

    public E getDefaultElement();
}
