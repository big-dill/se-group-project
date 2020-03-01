package se.uog.table;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * A TableCellEditor which allows users to choose values from a JList displayed
 * in a popup dialog. Loosely based on the ColorChooser from the swing tutorial:
 * http://www.java2s.com/Tutorials/Java/javax.swing/AbstractCellEditor/0040__AbstractCellEditor.AbstractCellEditor_.htm
 *
 * @param <T> The Table element class
 * @param <L> The List element class
 */
@SuppressWarnings("serial")
public class ObjectTableListSelector<T, L> extends AbstractCellEditor implements TableCellEditor {

    private JButton delegate = new JButton("editing...");

    // List selector instance variables
    private DefaultListModel<L> listElementList;
    private DefaultListModel<L> filteredList;
    private String dialogTitle;
    private List<L> selectedItems = new ArrayList<>();

    // Instance variables that allow filtering the displayed list using a filter
    // function.
    private DefaultListModel<T> tableElementList = null;
    private BiFunction<List<L>, T, List<L>> filterFunction = (listElementList, tableElement) -> listElementList;

    /**
     * A TableCellEditor which displays a list for selection given by the list model
     * provided.
     *
     * @param listElementList The DefaultListModel that the list is created from.
     * @param dialogTitle     The title of the dialog popup.
     */
    public ObjectTableListSelector(DefaultListModel<L> listElementList, String dialogTitle) {
        this(listElementList, null, dialogTitle, (targetList, tableElement) -> targetList);
    }

    /**
     * A TableCellEditor which displays a list for selection given by the list model
     * provided.
     *
     * This list can be filtered by a filter function which receives a copy of the
     * list model and the current row's object.
     *
     * @param listElementList  The DefaultListModel that the list is created from.
     * @param tableElementList A reference to the ObjectTableModel's list. This is
     *                         used to get the current row's object to pass to the
     *                         filter function.
     * @param dialogTitle      The title of the dialog popup.
     * @param filterFunction   A filter function in the form {@code (list, element)
     *                         -> list}. A copy of the listElementList is provided
     *                         to the function which can be modified using the
     *                         properties of the element. The returned list is then
     *                         displayed in the popup.
     *
     *                         For example, in a ObjectTableListSelector&lt;Hobby,
     *                         Person&gt;
     *
     *                         <code>
     *                         (hobbieList, person) -> {
     *                            Iterator<Hobby> iterator = hobbieList.iterator();
     *                              while(iterator.hasNext) {
     *                                  Hobby h = iterator.next();
     *                                  if(!h.equals(person.favouriteHobby()))
     *                                      iterator.remove();
     *                              }
     *                             return hobbieList;
     *                          }
     *                          </code>
     *
     *                         would display a selector list that only contains a
     *                         person's favourite hobby.
     *
     */
    public ObjectTableListSelector(DefaultListModel<L> listElementList, DefaultListModel<T> tableElementList,
            String dialogTitle, BiFunction<List<L>, T, List<L>> filterFunction) {

        this.listElementList = listElementList;
        this.tableElementList = tableElementList;
        this.dialogTitle = dialogTitle;
        this.filterFunction = filterFunction;

        // When the button is clicked, open a dialog with the possible list elements
        delegate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSelector();
            }
        });
    }

    /**
     * Shows the ListSelectorDialog from its singleton, then changes this element's
     * current selection.
     */
    private void showSelector() {
        // Pass the filtered list to the dialog as options.
        // Pass the current selection to set any previous selections in the dialog.
        List<L> selection = (List<L>) ListSelectorDialog.showDialog(delegate, dialogTitle, filteredList, selectedItems);
        changeSelection(selection);
    }

    /**
     * Change the user selection.
     *
     * @param selection the user selection
     */
    private void changeSelection(List<L> selection) {
        if (selection != null) {
            this.selectedItems = selection;
        }
        // Tells the JTable to stop editing when done.
        stopCellEditing();
    }

    @Override
    public Object getCellEditorValue() {
        // Return the selected items to the JTable model.
        return selectedItems;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        // Set the cell's current value to be the 'initial selection'.
        changeSelection((List<L>) value);

        T currentTableRowElement = null;
        // Get the current table element for filtering, if filtering is enabled.
        if (tableElementList != null) {
            currentTableRowElement = tableElementList.get(row);
        }
        // Apply the filter function to the listElementList and store it to be used by
        // the popup dialog. Some nasty conversion is needed from lists to
        // DefaultListModels.

        // TODO: Refactor to avoid bad conversions.
        filteredList = convertListToDefaultListModel(
                filterFunction.apply(convertDefaultListModelToList(listElementList), currentTableRowElement));

        // Show the 'editing...' button in the window while the dialog is open
        // Trigger the popup.
        return delegate;
    }

    // Helper functions for converting between DefaultListModel and Lists
    // TODO: Refactor these into a helper utility class

    private DefaultListModel<L> convertListToDefaultListModel(List<L> list) {
        DefaultListModel<L> listModel = new DefaultListModel<L>();
        for (L elem : list) {
            listModel.addElement(elem);
        }
        return listModel;
    }

    private List<L> convertDefaultListModelToList(DefaultListModel<L> listModel) {
        List<L> list = new ArrayList<L>();
        for (int i = 0; i < listModel.getSize(); i++) {
            list.add(listModel.getElementAt(i));
        }
        return list;
    }
}
