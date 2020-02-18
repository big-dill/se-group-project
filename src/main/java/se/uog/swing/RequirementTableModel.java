package se.uog.swing;

import javax.swing.DefaultComboBoxModel;

public class RequirementTableModel extends TableModel<Requirement> {

    private static final long serialVersionUID = 1L;

    private String[] columnNames = {"Qualification", "Teacher Name", "Approved"};

    public static final int QUALIFICATION_COLUMN = 0;
    public static final int TEACHER_COLUMN = 1;
    public static final int APPROVED_COLUMN = 2;

    public RequirementTableModel(DefaultComboBoxModel<Requirement> comboBoxModel,
            Requirement defaultElement) {
        super(comboBoxModel, defaultElement);
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
<<<<<<< HEAD
    public Object getColumnValue(Requirement element, int columnIndex) {
=======
    public Class<?> getColumnClass(int columnIndex) {
        // Return the class to swing, so it can generate appropriate controls for different
        // primitive types.
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue = null;
        Requirement requirement = listRequirement.get(rowIndex);

>>>>>>> parent of 3c32ae9... Add adder and remover
        switch (columnIndex) {
            case QUALIFICATION_COLUMN:
                return element.getQualificationName();
            case TEACHER_COLUMN:
                return element.getTeacher();
            case APPROVED_COLUMN:
                return element.isApproved();
            default:
                return null;
        }
    }

    @Override
<<<<<<< HEAD
    public void setColumnValue(Object value, Requirement element, int columnIndex) {
        switch (columnIndex) {
            case QUALIFICATION_COLUMN:
                element.setQualificationName((String) value);
                break;
            case TEACHER_COLUMN:
                element.setTeacher((Teacher) value);
                break;
            case APPROVED_COLUMN:
                element.setApproved((boolean) value);
=======
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
>>>>>>> parent of 3c32ae9... Add adder and remover
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // This can be overridden in subclasses, for example, if we want a particular user to
        // not be able to edit 'Approved' (as only the Administrator can do this).

<<<<<<< HEAD
        // In this case, teachers are not editable, nor is the ID.
        // return columnIndex != ID_COLUMN;
        return true;
=======
        // In this case, teachers are not editable.

        return columnIndex != TEACHER_COLUMN;
>>>>>>> parent of 3c32ae9... Add adder and remover
    }
}
