package se.uog.swing;

public class RequirementModel {

    private DefaultListModel<Requirement> requirementListModel;
    private TableModel<Requirement> requirementTableModel;


    requirementListModel=new DefaultListModel<Requirement>();requirementTableModel=new TableModel<Requirement>(requirementListModel,new Requirement("",null,true));

    requirementTableModel.addTableColumn(new TableModelColumn<Requirement>("Name",String.class,true,(requirement,value)->requirement.setQualificationName((String)value),(requirement)->requirement.getQualificationName()));

    requirementTableModel.addTableColumn(new TableModelColumn<Requirement>("Teacher",Teacher.class,true,(requirement,value)->requirement.setTeacher((Teacher)value),(requirement)->requirement.getTeacher()));
}
