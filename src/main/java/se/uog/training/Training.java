package se.uog.training;

import se.uog.IDReferenced;
import se.uog.qualification.Qualification;

import java.util.List;

public class Training extends IDReferenced {
    private String name;
    private List<Qualification> trainingQualificationList;

    public Training(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Qualification> getTrainingQualificationList() {
        return trainingQualificationList;
    }

    /**
     * Takes the Qualification input and sets that as the qualification to be gained through the training.
     *
     * @param trainingQualification
     */
    public void setTrainingQualificationList(List<Qualification> trainingQualification) {
        this.trainingQualificationList = trainingQualification;
    }

    @Override
    public String toString() {
        return name;
    }
}
