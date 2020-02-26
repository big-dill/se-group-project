package se.uog.model;

import java.util.UUID;

// qualification has a name
public class Qualification implements IDReferenced {
    private String id;
    private String qualificationName;

    // A constructor to create the qualification - only needs to be passed a string
    public Qualification(String s) {
        id = UUID.randomUUID().toString();
        qualificationName = s;

    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return qualificationName;
    }
}
