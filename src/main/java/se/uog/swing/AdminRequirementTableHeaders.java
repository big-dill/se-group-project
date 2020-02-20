package se.uog.swing;

public class AdminRequirementTableHeaders extends RequirementTableHeaders {
    @Override
    public int getColumnCount() {
        // Only return the first column
        return 3;
    }

    @Override
    public boolean isColumnEditable(int columnIndex) {
        // Can only edit the tick box at the end
        return columnIndex != RequirementTableHeaders.TEACHER_COLUMN
                || columnIndex != RequirementTableHeaders.QUALIFICATION_COLUMN;
    }
}
