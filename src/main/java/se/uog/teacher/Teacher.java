package se.uog.teacher;

import java.util.ArrayList;
import java.util.List;

import se.uog.IDReferenced;
import se.uog.qualification.Qualification;
import se.uog.training.Training;

// A teacher object has the following attributes: name, a list of qualifications, and possible training to be done. 
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
        this.pendingTraining = t;
    }

    public Training getTraining(){
        return pendingTraining;
    }

    public List<Qualification> getQualifications() {
        return teacherQualifications;
    }

    /**
     * Adds a qualification to the Teacher's list of qualifications. 
     * @param q
     */
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
