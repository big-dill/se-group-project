package se.uog.swing;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class RequirementTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private String[] columnNames = {"#", "Qualification", "Teacher Name", "Approved"};
    private final int ID_COLUMN = 0;
    private final int QUALIFICATION_COLUMN = 1;
    private final int TEACHER_COLUMN = 2;
    private final int APPROVED_COLUMN = 3;

    private List<Requirement> listRequirement = new ArrayList<Requirement>();

    public RequirementTableModel(List<Requirement> listRequirement) {
        this.listRequirement.addAll(listRequirement);
    }

    @Override
    public int getRowCount() {
        // Return the size of the array list
        return listRequirement.size();
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
        Object returnValue = null;
        Requirement requirement = listRequirement.get(rowIndex);

        switch (columnIndex) {
            case ID_COLUMN:
                returnValue = rowIndex + 1;
                break;
            case QUALIFICATION_COLUMN:
                returnValue = requirement.getQualificationName();
                break;
            case TEACHER_COLUMN:
                returnValue = requirement.getTeacherName();
                break;
            case APPROVED_COLUMN:
                returnValue = requirement.isApproved();
                break;
        }

        return returnValue;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Requirement requirement = listRequirement.get(rowIndex);

        switch (columnIndex) {
            case 1:
                requirement.setQualificationName((String) value);
                break;
            case 2:
                requirement.setTeacherName((String) value);
                break;
            case 3:
                requirement.setApproved((boolean) value);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // This can be overridden in subclasses, for example, if we want a particular user to
        // not be able to edit 'Approved' (as only the Administrator can do this).

        // In this case, teachers are not editable.

        return columnIndex != TEACHER_COLUMN;
    }
}
