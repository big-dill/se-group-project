package se.uog.swing;

public class Requirement {
    private String qualificationName;
    private Teacher teacher;
    private boolean isApproved;

    public Requirement(String qualificationName, Teacher teacher, boolean isApproved) {
        this.qualificationName = qualificationName;
        this.teacher = teacher;
        this.isApproved = isApproved;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}
