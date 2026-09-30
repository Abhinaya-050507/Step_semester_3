package week_6.class_problems;

public class Circulation {

    static class LibraryMember {

        protected String memberId;
        protected int borrowLimit;
        protected int booksBorrowed;

        public LibraryMember(
                String memberId,
                int borrowLimit) {

            this.memberId = memberId;
            this.borrowLimit = borrowLimit;
        }

        public void borrowBook() {
            if (booksBorrowed < borrowLimit)
                booksBorrowed++;
        }

        public int getBooksBorrowed() {
            return booksBorrowed;
        }

        public void displayInfo() {
            System.out.print(
                "General | Books: " +
                booksBorrowed
            );
        }
    }

    static class StudentMember extends LibraryMember {

        String course;

        public StudentMember(
                String memberId,
                int borrowLimit,
                String course) {

            super(memberId, borrowLimit);
            this.course = course;
        }

        @Override
        public void displayInfo() {
            System.out.print(
                "Student | Course: " +
                course +
                " | Books: " +
                booksBorrowed
            );
        }
    }

    static String batchPrint(
            LibraryMember[] members) {

        StringBuilder result =
            new StringBuilder();

        for (LibraryMember member : members) {

            member.displayInfo();

            if (member instanceof StudentMember) {

                StudentMember student =
                    (StudentMember) member;

                result.append(
                    "Course via downcast: "
                );

                result.append(student.course);
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        LibraryMember[] members = {

            new LibraryMember("LB5", 3),

            new StudentMember(
                "STU6", 3, "ECE"
            )
        };

        System.out.println(
            batchPrint(members)
        );
    }
}