package week_8.assigment_problems;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Q2_Submission {

    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }
    }

    static abstract class Assignment {

        String title;
        int maxMarks;
        LocalDate dueDate;

        public Assignment(
                String title,
                int maxMarks,
                LocalDate dueDate) {

            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDate = dueDate;
        }

        public abstract double applyPenalty(
                double marks,
                long lateDays);
    }

    static class CodingAssignment
            extends Assignment {

        public CodingAssignment(
                String title,
                int maxMarks,
                LocalDate dueDate) {

            super(title, maxMarks, dueDate);
        }

        @Override
        public double applyPenalty(
                double marks,
                long lateDays) {

            return marks * (1 - 0.10 * lateDays);
        }
    }

    static class WrittenAssignment
            extends Assignment {

        public WrittenAssignment(
                String title,
                int maxMarks,
                LocalDate dueDate) {

            super(title, maxMarks, dueDate);
        }

        @Override
        public double applyPenalty(
                double marks,
                long lateDays) {

            return marks * (1 - 0.20 * lateDays);
        }
    }

    static class Submission {

        Student student;
        Assignment assignment;
        LocalDate submissionDate;
        String status = "Submitted";
        double finalMarks;

        public Submission(
                Student student,
                Assignment assignment,
                LocalDate submissionDate) {

            this.student = student;
            this.assignment = assignment;
            this.submissionDate = submissionDate;

            long lateDays =
                Math.max(
                    0,
                    ChronoUnit.DAYS.between(
                        assignment.dueDate,
                        submissionDate
                    )
                );

            if (lateDays == 0) {
                System.out.println(
                    student.name
                    + "'s submission for '"
                    + assignment.title
                    + "' received (on time)."
                );
            } else {
                System.out.println(
                    student.name
                    + "'s submission for '"
                    + assignment.title
                    + "' received ("
                    + lateDays
                    + " days late)."
                );
            }

            System.out.println(
                "Status: " + status
            );
        }

        public void grade(double marks) {

            if (!status.equals("Submitted")) {
                return;
            }

            long lateDays =
                Math.max(
                    0,
                    ChronoUnit.DAYS.between(
                        assignment.dueDate,
                        submissionDate
                    )
                );

            finalMarks =
                assignment.applyPenalty(
                    marks,
                    lateDays
                );

            status = "Graded";

            if (lateDays == 0) {
                System.out.println(
                    student.name
                    + " graded: "
                    + finalMarks
                    + "/"
                    + assignment.maxMarks
                );
            } else {
                double penalty =
                    lateDays
                    * (assignment instanceof CodingAssignment
                    ? 10 : 20);

                System.out.println(
                    student.name
                    + " graded: "
                    + finalMarks
                    + "/"
                    + assignment.maxMarks
                    + " after "
                    + penalty
                    + "% late penalty."
                );
            }

            System.out.println(
                "Status: " + status
            );
        }

        public void resubmit() {

            if (status.equals("Graded")) {
                System.out.println(
                    "Cannot resubmit: '"
                    + assignment.title
                    + "' has already been graded."
                );
            }
        }
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding =
            new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10)
            );

        Assignment written =
            new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12)
            );

        Submission s1 =
            new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10)
            );

        Submission s2 =
            new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14)
            );

        s1.grade(45);
        s2.grade(40);

        s1.resubmit();
    }
}