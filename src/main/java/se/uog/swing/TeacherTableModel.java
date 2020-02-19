package se.uog.swing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;


public class TeacherTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private String[] columnNames = {"#", "Teacher Name", "Qualifications"};
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int QUALIFICATION_COLUMN = 2;

    private List<Teacher> listTeacher = new ArrayList<Teacher>();

    public TeacherTableModel(List<Teacher> listTeacher) {
        this.listTeacher.addAll(listTeacher);
    }

    @Override
    public int getRowCount() {
        // Return the size of the array list
        return listTeacher.size();
    }

    @Override
    public int getColumnCount() {
        // Return length of the names
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        // Reference columnNames
        return columnNames[columnIndex];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Return the class to swing, so it can generate appropriate controls for different
        // primitive types.
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // This gets the value from the model and puts it in the GUI. :O
        Object returnValue = null;
        Teacher teacher = listTeacher.get(rowIndex);

        switch (columnIndex) {
            case ID_COLUMN:
                returnValue = rowIndex + 1;
                break;
            case NAME_COLUMN:
                returnValue = teacher.getName();
                break;
            case QUALIFICATION_COLUMN:
                // Create comma seperated list by joining each qualification with a comma
                returnValue = teacher.getQualifications().stream().collect(Collectors.joining(","));
                break;
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        // This is used to set the value of the model item from the GUI. Sweet.
        Teacher teacher = listTeacher.get(rowIndex);

        switch (columnIndex) {
            case NAME_COLUMN:
                teacher.setName((String) value);
                break;
            case QUALIFICATION_COLUMN:
                String commaSeparatedList = (String) value
                teacher.();
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // This can be overridden in subclasses, for example, if we want a particular user to
        // not be able to edit 'Approved' (as only the Administrator can do this).

        // In this case, teachers are not editable, nor is the ID.
        return columnIndex != TEACHER_COLUMN || columnIndex != ID_COLUMN;
    }

    public void addRow(Teacher teacher) {
        // Add to our list
        listTeacher.add(teacher);
        // Set the index to be the 'last' row
        int rowIndex = getRowCount();
        // Update the view by letting it know we've added a table row.
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    public void removeRow(int rowIndex) {
        // Remove the requirement with the row index
        listTeacher.remove(rowIndex);
        // Update the view by letting it know we've nuked a table row.
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


}
