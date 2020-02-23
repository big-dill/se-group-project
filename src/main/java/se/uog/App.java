package se.uog;

import se.AppModel.*;


public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        // Manual acceptance tests to check functionality of base classes 

        Qualification englishA = new Qualification("EnglishTeacher");
        Qualification maths = new Qualification("MathsTeacher");
        Qualification primaryTeacher = new Qualification("Primary Teacher");

        
        Course englishLit = new Course("English Literature");
        Course algebra1 = new Course("Algebra 1");

        englishLit.addRequirement(englishA);
        englishLit.addRequirement(primaryTeacher);
        algebra1.addRequirement(maths);

        Teacher gareth = new Teacher("Gareth");
        Teacher hugh = new Teacher ("Hugh");

        gareth.addQualification(englishA);
        hugh.addQualification(maths);


        // should return false false true 
        System.out.println(englishLit.assignTeacher(gareth));
        System.out.println(englishLit.assignTeacher(hugh));

        gareth.addQualification(primaryTeacher);
        
        System.out.println(englishLit.assignTeacher(gareth));






       
    }
}
