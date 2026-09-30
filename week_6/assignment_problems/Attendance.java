package week_6.assignment_problems;

public class Attendance {

    static class GymMember {

        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(
                String memberId,
                int monthlyFee) {

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public void displayInfo() {
            System.out.print(
                "Standard | Sessions: "
                + sessionsAttended
            );
        }
    }

    static class PremiumMember extends GymMember {

        protected String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        public void displayInfo() {
            System.out.print(
                "Premium | Trainer: "
                + trainerName
                + " | Sessions: "
                + sessionsAttended
            );
        }
    }

    static String batchPrint(GymMember[] members) {

        StringBuilder result =
            new StringBuilder();

        for (GymMember member : members) {

            member.displayInfo();

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                    (PremiumMember) member;

                result.append(
                    "[Trainer via downcast: "
                    + premium.trainerName
                    + "]"
                );
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {

            new GymMember("MEM6", 1000),

            new PremiumMember(
                "MEM7",
                2000,
                "Coach Riya"
            )
        };

        System.out.println(
            batchPrint(members)
        );
    }
}