package se.uog.swing;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class RequirementTeacherCellEditor extends AbstractCellEditor implements TableCellEditor {

    DefaultCellEditor checkbox;

    private DefaultCellEditor lastSelected;

    @Override
    public Object getCellEditorValue() {
        return lastSelected.getCellEditorValue();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {

        String qualification = (String) table.getModel().getValueAt(row,
                RequirementTableModel.QUALIFICATION_COLUMN);

        // Teachers with that row's qualification
        new JComboBox(new Object[] {"abc"});


        DefaultCellEditor teacherWithQualificationList = new DefaultCellEditor();


        return null;
    }

}
