package se.uog.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Teacher implements IDReferenced {
    private String id;
    private String name;
    private List<Qualification> teacherQualifications = new ArrayList<Qualification>();

    public Teacher(String n) {
        id = UUID.randomUUID().toString();
        name = n;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
