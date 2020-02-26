package se.uog.model;

import java.util.List;

public class Training extends IDReferenced {
    private String name;
    private Qualification trainingQualification;
    private List<Teacher> assignedTeachers;

    public Training(String n) {

        name = n;
    }

    public void trainTeacher(Teacher teacher) {
        teacher.addQualification(trainingQualification);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     Qualification getTrainingQualification() {
        return trainingQualification;
    }

     void setTrainingQualification(Qualification trainingQualification) {
        this.trainingQualification = trainingQualification;
    }

     List<Teacher> getAssignedTeachers() {
        return assignedTeachers;
    }

     void setAssignedTeachers(List<Teacher> assignedTeachers) {
        this.assignedTeachers = assignedTeachers;
    }
}
