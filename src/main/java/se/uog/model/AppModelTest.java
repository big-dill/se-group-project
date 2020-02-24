package se.uog.model;

/* Test code for Core classes */

public final class AppModelTest {
    private AppModelTest() {
    }

    public static void main(String[] args) {

        Qualification englishA = new Qualification("EnglishTeacher");
        Qualification maths = new Qualification("MathsTeacher");
        Qualification primaryTeacher = new Qualification("Primary Teacher");


        Course englishLit = new Course("English Literature");
        Course algebra1 = new Course("Algebra 1");

        englishLit.addRequirement(englishA);
        englishLit.addRequirement(primaryTeacher);
        algebra1.addRequirement(maths);

        Teacher gareth = new Teacher("Gareth");
        Teacher hugh = new Teacher("Hugh");

        gareth.addQualification(englishA);
        hugh.addQualification(maths);


        // should return false false true
        System.out.println(englishLit.assignTeacher(gareth));
        System.out.println(englishLit.assignTeacher(hugh));

        gareth.addQualification(primaryTeacher);

        System.out.println(englishLit.assignTeacher(gareth));



    }

}
