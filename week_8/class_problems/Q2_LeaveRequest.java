package week_8.class_problems;

public class Q2_LeaveRequest {

    static abstract class Employee {

        protected String name;

        public Employee(String name) {
            this.name = name;
        }

        public abstract boolean canTakeLeave(int days);
    }

    static class FullTimeEmployee extends Employee {

        public FullTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean canTakeLeave(int days) {
            return days <= 20;
        }
    }

    static class PartTimeEmployee extends Employee {

        public PartTimeEmployee(String name) {
            super(name);
        }

        @Override
        public boolean canTakeLeave(int days) {
            return days <= 10;
        }
    }

    static class Contractor extends Employee {

        public Contractor(String name) {
            super(name);
        }

        @Override
        public boolean canTakeLeave(int days) {
            return days <= 5;
        }
    }

    static class LeaveRequest {

        Employee employee;
        String startDate;
        String endDate;
        int days;
        String status;

        public LeaveRequest(
                Employee employee,
                String startDate,
                String endDate,
                int days) {

            this.employee = employee;
            this.startDate = startDate;
            this.endDate = endDate;
            this.days = days;
            status = "Pending";
        }

        public void approve() {

            if (status.equals("Pending")) {
                status = "Approved";

                System.out.println(
                    employee.name + "'s leave request ("
                    + startDate + "-" + endDate
                    + ") approved."
                );

                System.out.println("Status: " + status);
            }
        }

        public void reject() {

            if (status.equals("Pending")) {
                status = "Rejected";

                System.out.println(
                    employee.name + "'s leave request ("
                    + startDate + "-" + endDate
                    + ") rejected."
                );

                System.out.println("Status: " + status);
            }
        }

        public void changeStatus(String newStatus) {

            if (!status.equals("Pending")) {
                System.out.println(
                    "Cannot change leave request status from "
                    + status + " to " + newStatus + "."
                );
                return;
            }

            status = newStatus;
        }
    }

    static LeaveRequest submitLeave(
            Employee employee,
            String startDate,
            String endDate,
            int days) {

        if (!employee.canTakeLeave(days)) {
            System.out.println("Leave request rejected by policy.");
            return null;
        }

        LeaveRequest request =
            new LeaveRequest(
                employee,
                startDate,
                endDate,
                days
            );

        System.out.println(
            "Leave request submitted for "
            + employee.name
            + " (" + startDate + "-" + endDate + ")."
        );

        System.out.println("Status: " + request.status);

        return request;
    }

    public static void main(String[] args) {

        Employee john =
            new FullTimeEmployee("John");

        LeaveRequest johnRequest =
            submitLeave(
                john,
                "Jan 1",
                "Jan 5",
                5
            );

        johnRequest.approve();

        johnRequest.changeStatus("Pending");

        Employee jane =
            new PartTimeEmployee("Jane");

        LeaveRequest janeRequest =
            submitLeave(
                jane,
                "Feb 10",
                "Feb 11",
                2
            );

        janeRequest.reject();
    }
}