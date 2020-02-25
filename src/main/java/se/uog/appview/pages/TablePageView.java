package se.uog.appview.pages;

import se.uog.table.ObjectTableModel;

/**
 * Add this interface to guarantee that a page's TableModel can be 'set' from the outside.
 */
public interface TablePageView<E> {
    public void setTableModel(ObjectTableModel<E> model);
}
