package week_4.class_problems;

class SrmStudent {
    String name;

    static String collegeName;
    static int academicYear;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = 2026;
        System.out.println("College info loaded");
    }

    SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + name);
    }
}

public class M4_OneTimeCollegeSetupManyStudents {
    public static void main(String[] args) {

        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};

        for (String name : names)
            new SrmStudent(name);
    }
}