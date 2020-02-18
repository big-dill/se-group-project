package se.uog.swing;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private String name;
    private List<String> qualifications;

    public Teacher(String name) {
        this.name = name;
        qualifications = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void addQualification(String qualification) {
        qualifications.add(qualification);
    }

    /*
     * @return the qualifications
     */
    public List<String> getQualifications() {
        return qualifications;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}
