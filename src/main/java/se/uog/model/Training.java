package se.uog.model;

public class Training extends IDReferenced {
    private String name;
    private Qualification trainingQualification;

    public Training(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Qualification getTrainingQualification() {
        return trainingQualification;
    }

    /**
     * Takes the Qualification input and sets that as the qualification to be gained through the training.
     * @param trainingQualification
     */
    public void setTrainingQualification(Qualification trainingQualification) {
        this.trainingQualification = trainingQualification;
    }
}
