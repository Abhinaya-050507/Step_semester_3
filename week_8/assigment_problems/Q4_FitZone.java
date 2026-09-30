package week_8.assigment_problems;
public class Q4_FitZone {

    interface MembershipPlan {

        double calculateFee();
        String getName();
    }

    static class MonthlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000;
        }

        public String getName() {
            return "Monthly";
        }
    }

    static class QuarterlyPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 3 * 0.90;
        }

        public String getName() {
            return "Quarterly";
        }
    }

    static class AnnualPlan implements MembershipPlan {

        public double calculateFee() {
            return 1000 * 12 * 0.75;
        }

        public String getName() {
            return "Annual";
        }
    }

    static class Member {

        String name;

        public Member(String name) {
            this.name = name;
        }
    }

    static class Membership {

        Member member;
        MembershipPlan plan;
        private String status;

        public Membership(
                Member member,
                MembershipPlan plan) {

            this.member = member;
            this.plan = plan;
            status = "Active";

            System.out.println(
                plan.getName()
                + " membership created for "
                + member.name + "."
            );

            System.out.printf(
                "Fee: ₹%.2f%n",
                plan.calculateFee()
            );

            System.out.println(
                "Status: " + status
            );
        }

        public void checkIn() {

            if (status.equals("Active")) {
                System.out.println(
                    member.name
                    + " checked in successfully."
                );
            } else {
                System.out.println(
                    "Check-in denied: "
                    + member.name
                    + "'s membership is "
                    + status + "."
                );
            }
        }

        public void freeze() {

            if (status.equals("Active")) {

                status = "Frozen";

                System.out.println(
                    member.name
                    + "'s membership frozen."
                );

                System.out.println(
                    "Status: " + status
                );

            } else if (status.equals("Expired")) {

                System.out.println(
                    "Cannot freeze an Expired membership."
                );
            }
        }

        public void unfreeze() {

            if (status.equals("Frozen")) {

                status = "Active";

                System.out.println(
                    member.name
                    + "'s membership unfrozen."
                );

                System.out.println(
                    "Status: " + status
                );

            } else if (status.equals("Expired")) {

                System.out.println(
                    "Cannot unfreeze an Expired membership."
                );
            }
        }

        public void expire() {

            status = "Expired";

            System.out.println(
                member.name
                + "'s membership expired."
            );

            System.out.println(
                "Status: " + status
            );
        }
    }

    public static void main(String[] args) {

        Member asha =
            new Member("Asha");

        Member ravi =
            new Member("Ravi");

        Membership ashaMembership =
            new Membership(
                asha,
                new QuarterlyPlan()
            );

        Membership raviMembership =
            new Membership(
                ravi,
                new MonthlyPlan()
            );

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}