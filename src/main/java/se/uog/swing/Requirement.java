package se.uog.swing;

public class Requirement {
    private String qualificationName;
    private String teacherName;
    private boolean isApproved;

    public Requirement(String qualificationName, String teacherName, boolean isApproved) {
        this.qualificationName = qualificationName;
        this.teacherName = teacherName;
        this.isApproved = isApproved;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}
