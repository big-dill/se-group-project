package se.uog.table;

import java.util.List;
import javax.swing.DefaultListModel;

public interface ObjectTableConfiguration<E> {

    public DefaultListModel<E> getListModel();

    public E createDefaultElement();

    public List<ObjectTableColumn<E>> getObjectColumnMap();
}
