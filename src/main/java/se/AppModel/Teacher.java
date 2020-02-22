package se.AppModel;

import java.util.ArrayList;


public class Teacher {
    protected String name;
    public ArrayList<Qualification> teacherQualifications;

    public Teacher(String n) {
        name = n;
        teacherQualifications = new ArrayList<Qualification>();
    }

    public ArrayList<Qualification> getQualification (){
        return teacherQualifications;
    }

    public void addQualification(Qualification q){
        teacherQualifications.add(q);
    }

    


}