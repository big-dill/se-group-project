package se.uog.swing;

public abstract class TableController {

    TableModel model;
    TableView view;

    public TableController(TableModel model) {

    }

    public void addView(TableView view) {
        this.view = view;
    }

    public abstract void addRow();

    public abstract void removeRow(int rowIndex);

}
