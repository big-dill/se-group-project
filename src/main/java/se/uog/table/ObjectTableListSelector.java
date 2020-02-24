package se.uog.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ObjectTableListSelector<TE, LE> extends AbstractCellEditor
        implements TableCellEditor, ActionListener {

    private JButton delegate = new JButton("editing...");

    private DefaultListModel<LE> listElementList;
    private DefaultListModel<LE> filteredList;
    private String dialogTitle;
    private List<LE> selectedItems = new ArrayList<>();

    // FILTERING INSTANCE VARIABLES:

    // The table model list is needed to get the element in the corresponding row.
    // Defaults to null as it is not needed.
    private DefaultListModel<TE> tableElementList = null;
    // Filter function just returns original listElementList;
    private BiFunction<List<LE>, TE, List<LE>> filterFunction =
            (listElementList, tableElement) -> listElementList;

    public ObjectTableListSelector(DefaultListModel<LE> listElementList, String dialogTitle) {
        // Defaults to no filter function
        this(listElementList, null, dialogTitle, (targetList, tableElement) -> targetList);
    }

    public ObjectTableListSelector(DefaultListModel<LE> listElementList,
            DefaultListModel<TE> tableElementList, String dialogTitle,
            BiFunction<List<LE>, TE, List<LE>> filterFunction) {

        this.listElementList = listElementList;
        this.tableElementList = tableElementList;
        this.dialogTitle = dialogTitle;
        this.filterFunction = filterFunction;

        // When the button is clicked, open a dialog with the possible list elements
        delegate.addActionListener(this);
    }

    // Change if something is selected, otherwise,
    private void changeSelection(List<LE> selectedItems) {
        if (selectedItems != null) {
            this.selectedItems = selectedItems;
        }

        stopCellEditing();
    }

    @Override
    public Object getCellEditorValue() {
        // Return the selected items to the model
        return selectedItems;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {

        TE currentTableRowElement = null;
        // Get the current table element for filtering etc.
        if (tableElementList != null) {
            currentTableRowElement = tableElementList.get(row);
        }
        // Filter the listElementList based on the provided function
        filteredList = convertListToDefaultListModel(filterFunction
                .apply(convertDefaultListModelToList(listElementList), currentTableRowElement));

        // Show the 'editing...' button in the window while the dialog is open
        return delegate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // OPEN THE LIST SELECTOR HERE
        List<LE> selection = (List<LE>) ListSelectorDialog.showDialog(delegate, dialogTitle,
                filteredList, selectedItems);

        changeSelection(selection);
    }

    private DefaultListModel<LE> convertListToDefaultListModel(List<LE> list) {
        DefaultListModel<LE> listModel = new DefaultListModel<LE>();
        for (LE elem : list) {
            listModel.addElement(elem);
        }
        return listModel;
    }

    private List<LE> convertDefaultListModelToList(DefaultListModel<LE> listModel) {
        List<LE> list = new ArrayList<LE>();
        for (int i = 0; i < listModel.getSize(); i++) {
            list.add(listModel.getElementAt(i));
        }
        return list;
    }
}
