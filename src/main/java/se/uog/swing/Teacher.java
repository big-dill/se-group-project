package se.uog.swing;

public class Teacher {
    String name;
    boolean isCool;
    int age;

    public Teacher(String name, boolean isCool, int age) {
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

}
