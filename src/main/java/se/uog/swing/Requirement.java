package se.uog.swing;

import java.util.ArrayList;
import java.util.List;

public class Requirement {
    private String qualificationName;
    private List<Teacher> teacherList = new ArrayList<>();
    private boolean isApproved;

    public Requirement(String qualificationName, List<Teacher> teacherList, boolean isApproved) {
        this.qualificationName = qualificationName;
        this.teacherList = teacherList;
        this.isApproved = isApproved;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public List<Teacher> getTeachers() {
        return teacherList;
    }

    public void setTeachers(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

}
