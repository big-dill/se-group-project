package se.uog.swing;

import java.util.List;

public class Teacher {
    private String name;
    private List<String> qualifications;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addQualification(String qualification) {
        qualifications.add(qualification);
    }

    public List<String> getQualifications() {
        return qualifications;
    }
}
