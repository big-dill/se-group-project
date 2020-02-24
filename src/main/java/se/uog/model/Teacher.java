package se.uog.model;

import java.util.ArrayList;
import java.util.List;


public class Teacher {
    private String name;
    private List<Qualification> teacherQualifications = new ArrayList<Qualification>();

    public Teacher(String n) {
        name = n;
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

}
