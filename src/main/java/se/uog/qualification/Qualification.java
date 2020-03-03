package se.uog.qualification;

import se.uog.database.IDReferenced;

// qualification has a name
public class Qualification extends IDReferenced {
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
        return qualificationName;
    }
}
