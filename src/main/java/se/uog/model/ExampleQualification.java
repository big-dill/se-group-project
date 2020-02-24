package se.uog.model;

public class ExampleQualification {
    private String qualificationName;

    public ExampleQualification(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getName() {
        return qualificationName;
    }

    public void setName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    @Override
    public String toString() {
        return qualificationName;
    }
}
