package se.uog.table;

import javax.swing.*;
import java.util.List;

/**
 * An interface which ensures that an ObjectTable receives the required information so that it can
 * be built.
 */
public interface ObjectTableModel<E> {

    /**
     * Returns the DefaultListModel which the ObjectTable is tied to. The ObjectTable is registered
     * as a listener of this object, so when it is updated, the table is synchronised.
     */
    DefaultListModel<E> getListModel();

    /**
     * Returns the default object to be created when an ObjectTable adds a new row to the listModel.
     * This can subsequently be edited within the table.
     */
    E createDefaultElement();

    /**
     * This tells the table how to map its columns to the underlying object's attributes. It is
     * expected to return a list of these mappings. The table will initially display the columns in
     * this order.
     *
     * @see se.uog.table.ObjectTableColumn
     */
    List<ObjectTableColumn<E>> getObjectColumnMap();
}
