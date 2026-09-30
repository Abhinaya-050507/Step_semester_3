package week_6.assignment_problems;

public class LoyaltyLedger {

    static class GymMember {

        protected String memberId;
        protected int monthlyFee;

        private int[] lateFeeHistory = new int[10];
        private int feeCount;

        public GymMember(
                String memberId,
                int monthlyFee) {

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        protected void chargeLateFee(int amount) {

            if (feeCount < 10) {
                lateFeeHistory[feeCount] = amount;
                feeCount++;
            }
        }

        public int[] getLateFeeHistory() {

            int[] copy = new int[feeCount];

            for (int i = 0; i < feeCount; i++)
                copy[i] = lateFeeHistory[i];

            return copy;
        }

        public int getTotalLateFees() {

            int total = 0;

            for (int i = 0; i < feeCount; i++)
                total += lateFeeHistory[i];

            return total;
        }
    }

    static class PremiumMember extends GymMember {

        String trainerName;

        public PremiumMember(
                String memberId,
                int monthlyFee,
                String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        @Override
        protected void chargeLateFee(int amount) {
            super.chargeLateFee(amount / 2);
        }
    }

    public static void main(String[] args) {

        PremiumMember p =
            new PremiumMember(
                "MEM5",
                2000,
                "Coach Riya"
            );

        p.chargeLateFee(200);

        System.out.println(
            p.getTotalLateFees()
        );

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
            p.getLateFeeHistory()[0]
        );
    }
}