package se.uog.swing.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class TableListEditor extends AbstractCellEditor implements TableCellEditor {

    private JButton delegate = new JButton("view");
    private List<?> selectedItems;

    public TableListEditor(DefaultListModel<?> listModel, String title) {

        // When the button is clicked, open a dialog with the possible list elements
        delegate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

                // OPEN THE LIST SELECTOR HERE
                List<?> selection = ListDialog.showDialog(
                    delegate,
                    title,
                    listModel,
                    selectedItems);
                changeSelection(selection);
            }
        });
    }

    // Change if something is selected, otherwise,
    private void changeSelection(List<?> selectedItems) {
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

        // Set the selected items from what is provided by the TableModel.
        changeSelection((List<?>) value);
        return delegate;
    }

}
