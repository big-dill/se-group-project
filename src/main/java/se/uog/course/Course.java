package se.uog.course;

import java.util.ArrayList;
import java.util.List;

import se.uog.qualification.Qualification;
import se.uog.teacher.Teacher;

public class Course {
    private String name;
    private List<Qualification> requirements = new ArrayList<Qualification>();
    private List<Teacher> assignedTeachers;

    public Course(String n) {
        name = n;
    }

    /**
     * method to add a qualification to the requirement
     * @param q
     */

    public void addRequirement(Qualification q) {
        requirements.add(q);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Qualification> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Qualification> requirements) {
        this.requirements = requirements;
    }

    public List<Teacher> getAssignedTeachers() {
        return assignedTeachers;
    }

    public void setAssignedTeachers(List<Teacher> assignedTeachers) {
        this.assignedTeachers = assignedTeachers;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }

}
