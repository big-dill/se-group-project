package se.uog.swing.table;

import java.util.ArrayList;
import java.util.List;
import se.uog.swing.Requirement;
import se.uog.swing.Teacher;

public class RequirementTableConfiguration implements TablePanelConfiguration<Requirement> {

    final static int QUALIFICATION_COLUMN = 0;
    final static int TEACHER_COLUMN = 1;
    final static int APPROVED_COLUMN = 2;

    String[] columnNames = new String[] {"Qualification", "Teachers", "Approved"};
    Class<?>[] columnClasses = new Class<?>[] {String.class, List.class, Boolean.class};

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Requirement getDefaultElement() {
        return new Requirement("", new ArrayList<Teacher>(), false);
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public Class<?>[] getColumnClasses() {
        return columnClasses;
    }

    @Override
    public boolean isColumnEditable(int columnIndex) {
        return true;
    }

    @Override
    public Object columnGetter(Requirement element, int columnIndex) {
        switch (columnIndex) {
            case QUALIFICATION_COLUMN:
                return element.getQualificationName();

            case TEACHER_COLUMN:
                return element.getTeachers();

            case APPROVED_COLUMN:
                return element.isApproved();

            default:
                return null;
        }
    }

    @Override
    public void columnSetter(Requirement element, Object value, int columnIndex) {
        switch (columnIndex) {
            case QUALIFICATION_COLUMN:
                element.setQualificationName((String) value);
                return;

            case TEACHER_COLUMN:
                element.setTeachers((List<Teacher>) value);
                return;

            case APPROVED_COLUMN:
                element.setApproved((boolean) value);
                return;

            default:
                return;
        }
    }
}
