package se.uog.swing;

public class CDRequirementTableHeaders extends RequirementTableHeaders {
    @Override
    public int getColumnCount() {
        // Only return the first column
        return 1;
    }
}
