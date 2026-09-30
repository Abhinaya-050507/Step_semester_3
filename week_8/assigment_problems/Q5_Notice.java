package week_8.assigment_problems;

public class Q5_Notice {

    interface NotificationChannel {

        String send(Student student, Notice notice);
    }

    static class EmailChannel
            implements NotificationChannel {

        public String send(
                Student student,
                Notice notice) {

            return "[Email → "
                    + student.name + "] "
                    + notice.title;
        }
    }

    static class SmsChannel
            implements NotificationChannel {

        public String send(
                Student student,
                Notice notice) {

            return "[SMS → "
                    + student.name + "] "
                    + notice.title;
        }
    }

    static class AppChannel
            implements NotificationChannel {

        public String send(
                Student student,
                Notice notice) {

            return "[App → "
                    + student.name + "] "
                    + notice.title;
        }
    }

    static class Student {

        String name;
        String department;

        ArrayList<NotificationChannel> channels =
            new ArrayList<>();

        public Student(
                String name,
                String department) {

            this.name = name;
            this.department = department;
        }

        public void addChannel(
                NotificationChannel channel) {

            channels.add(channel);
        }
    }

    static class Notice {

        String title;
        ArrayList<String> departments =
            new ArrayList<>();

        public Notice(
                String title,
                String... departments) {

            this.title = title;

            for (String department : departments) {
                this.departments.add(department);
            }
        }

        public boolean isValid() {

            return title != null
                    && !title.trim().isEmpty()
                    && !departments.isEmpty();
        }

        public boolean targets(String department) {
            return departments.contains(department);
        }
    }

    static class NoticeBoard {

        ArrayList<Student> students =
            new ArrayList<>();

        public void addStudent(Student student) {
            students.add(student);
        }

        public void postNotice(Notice notice) {

            if (!notice.isValid()) {
                System.out.println(
                    "Cannot post notice: At least one target department is required."
                );
                return;
            }

            System.out.print(
                "Notice '" + notice.title
                + "' posted to "
            );

            for (int i = 0;
                 i < notice.departments.size();
                 i++) {

                System.out.print(
                    notice.departments.get(i)
                );

                if (i < notice.departments.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println(".");

            for (Student student : students) {

                if (notice.targets(
                        student.department)) {

                    for (NotificationChannel channel
                            : student.channels) {

                        System.out.println(
                            channel.send(
                                student,
                                notice
                            )
                        );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        NoticeBoard board =
            new NoticeBoard();

        Student asha =
            new Student("Asha", "CSE");

        Student ravi =
            new Student("Ravi", "ECE");

        asha.addChannel(
            new EmailChannel()
        );

        asha.addChannel(
            new AppChannel()
        );

        ravi.addChannel(
            new SmsChannel()
        );

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice notice1 =
            new Notice(
                "Lab Closed Tomorrow",
                "CSE"
            );

        Notice notice2 =
            new Notice(
                "Fee Deadline Extended",
                "CSE",
                "ECE"
            );

        Notice notice3 =
            new Notice("Sports Day");

        board.postNotice(notice1);

        board.postNotice(notice2);

        board.postNotice(notice3);
    }
}