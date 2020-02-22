package se.AppModel;
import java.util.ArrayList;
public class Course {
    String name;
    ArrayList<Qualification> requirements;
    Teacher assignedTeacher;

    public Course (String n) {
        name = n; 
        requirements = new ArrayList<Qualification>();
    }

    //method to add a teacher to the course (but only if theyre qualifications meet the requirement)
    public boolean assignTeacher(Teacher t) {
        boolean assigned = false;

        for ( Qualification f : requirements) {
            if (t.getQualification().contains(f) == false){
                return assigned;
            } 

        }
        assigned = true;
        assignedTeacher = t;
        return assigned;
    }

    //method to add a qualification to the requirement
    public void addRequirement(Qualification q){
        requirements.add(q);
    }


}