package se.uog.swing;

public class RequirementTableController extends TableController {

    public RequirementTableController(RequirementTableModel model) {
        super(model);
    }

    @Override
    public void addRow() {
        model.addDefaultRow();

    }

    @Override
    public void removeRow(int rowIndex) {
        model.removeRow(rowIndex);
    }
}
