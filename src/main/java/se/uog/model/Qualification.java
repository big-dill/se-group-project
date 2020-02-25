package se.uog.model;

// qualification has a name
public class Qualification {
    private String qualificationName;

    // A constructor to create the qualification - only needs to be passed a string
    public Qualification(String s) {
        qualificationName = s;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return qualificationName;
    }
}
