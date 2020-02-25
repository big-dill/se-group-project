package se.uog.model;

public class Training {
    private String name;
    private Qualification trainingQualification;

    public Training (String n) {
        name = n;
    }
    

    public void trainTeacher (Teacher teacher) {
        teacher.addQualification(trainingQualification);
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

    public void setTrainingQualification(Qualification trainingQualification) {
        this.trainingQualification = trainingQualification;
    }

}