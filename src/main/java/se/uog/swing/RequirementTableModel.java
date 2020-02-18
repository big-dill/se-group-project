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
    public Object getColumnValue(Requirement element, int columnIndex) {
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
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // This can be overridden in subclasses, for example, if we want a particular user to
        // not be able to edit 'Approved' (as only the Administrator can do this).

        // In this case, teachers are not editable, nor is the ID.
        // return columnIndex != ID_COLUMN;
        return true;
    }
}
