package se.uog.swing;

import java.util.ArrayList;
import java.util.List;

public class ExampleTeacher {
    String name;
    boolean isCool;
    int age;
    List<ExampleQualification> qualificationList = new ArrayList<>();

    public ExampleTeacher(String name, boolean isCool, int age) {
        this.name = name;
        this.isCool = isCool;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCool() {
        return isCool;
    }

    public void setCool(boolean isCool) {
        this.isCool = isCool;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ExampleQualification> getQualifications() {
        return qualificationList;
    }

    public void setQualifications(List<ExampleQualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @Override
    public String toString() {
        return String.format("%s : %d", name, age);
    }
}
