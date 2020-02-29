package se.uog.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends IDReferenced {
    private String name;
    private List<Qualification> teacherQualifications = new ArrayList<Qualification>();
    private Training pendingTraining;

    public Teacher(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTraining(Training t){
        pendingTraining = t;
    }

    public Training getTraining(){
        return pendingTraining;
    }

    public List<Qualification> getQualifications() {
        return teacherQualifications;
    }

    public void addQualification(Qualification q) {
        teacherQualifications.add(q);
    }

    public void clearQualifications() {
        teacherQualifications.clear();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}
