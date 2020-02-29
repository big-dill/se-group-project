package se.uog.teacher;

import se.uog.IDReferenced;
import se.uog.qualification.Qualification;
import se.uog.training.Training;

import java.util.ArrayList;
import java.util.List;

// A teacher object has the following attributes: name, a list of qualifications, and possible training to be done.
public class Teacher extends IDReferenced {
    private String name;
    private List<Qualification> teacherQualifications = new ArrayList<Qualification>();
    private List<Training> pendingTraining = new ArrayList<Training>();

    public Teacher(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a training to the Teacher's list of training to be completed
     *
     * @param t
     */
    public void addTraining(Training t){
        pendingTraining.add(t);
    }

    public void setTraining(List<Training> t) {
        this.pendingTraining = t;
    }

    public List<Training> getTraining(){
        return pendingTraining;
    }

    public void clearTraining() {
        pendingTraining.clear();
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
        return name;
    }
}
