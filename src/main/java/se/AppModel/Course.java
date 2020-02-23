package se.AppModel;
import java.util.ArrayList;
public class Course {
    private String name;
    private ArrayList<Qualification> requirements;
    private Teacher assignedTeacher;

    public Course (String n) {
        name = n; 
        requirements = new ArrayList<Qualification>();
    }

    //method to add a teacher to the course (but only if theyre qualifications meet the requirement)
    // this method iterates through the requirements arraylist and checks if the Teacher has the qualification
    // if there is a Qualification that is not contained in the teacher's qualifications then the method does nothing 
    // and returns false 
    // if the teacher has all of the requirements then they are assigned to the course and the method returns true 
    // boolean was for testing - most likely will get changed 
    public boolean assignTeacher(Teacher t) {

        for ( Qualification f : requirements) {
            if (!t.getQualifications().contains(f)){
                return false;
            } 
        }
        
        assignedTeacher = t;
        return true;
    }

    //method to add a qualification to the requirement
    public void addRequirement(Qualification q){
        requirements.add(q);
    }


}