package se.AppModel;

import java.util.ArrayList;


public class Teacher {
    private String name;
    private ArrayList<Qualification> teacherQualifications;

    public Teacher(String n) {
        name = n;
        teacherQualifications = new ArrayList<Qualification>();
    }

    public ArrayList<Qualification> getQualifications (){
        return teacherQualifications;
    }

    public void addQualification(Qualification q){
        teacherQualifications.add(q);
    }

    


}