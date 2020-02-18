package se.uog.swing;

import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class RequirementTableView extends TableView {

    public RequirementTableView(RequirementTableModel requirementTableModel,
            RequirementTableController requirementTableController, ArrayList<Teacher> teacherList) {
        super(requirementTableModel, requirementTableController);

        // Get the table from TableView
        JTable table = getTable();


        List<Teacher> result = teacherList.stream() // convert list to stream
                .filter(teacher -> teacher.getQualifications().contains()) // we dont like mkyong
                .collect(Collectors.toList());

        // Set the teacher to be selected as a combobox.
        JComboBox<Teacher> comboBox =
                new JComboBox<Teacher>(listTeacher.toArray(new Teacher[listTeacher.size()]));

        TableCellEditor teacherCellEditor = new DefaultCellEditor(comboBox);

        table.getColumnModel().getColumn(RequirementTableModel.TEACHER_COLUMN)
                .setCellEditor(teacherCellEditor);

    }

}
