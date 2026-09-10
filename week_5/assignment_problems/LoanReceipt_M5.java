package week_5.assignment_problems;

public class LoanReceipt_M5 {

    static class LoanReceipt {

        private final String memberId;
        private final String[] bookIds;

        public LoanReceipt(String memberId, String[] bookIds) {
            this.memberId = memberId;
            this.bookIds = bookIds.clone();
        }

        public String[] getBookIds() {
            return bookIds.clone();
        }

        public LoanReceipt withCorrectedBookId(
                int index, String newId) {

            String[] copy = bookIds.clone();

            copy[index] = newId;

            return new LoanReceipt(memberId, copy);
        }
    }

    static class ReferenceOnlyLoanReceipt
            extends LoanReceipt {

        private String roomNumber;

        public ReferenceOnlyLoanReceipt(
                String memberId,
                String[] bookIds,
                String roomNumber) {

            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }
    }

    static class CirculationLedger {

        static String branchCode;

        static {
            branchCode = "PT-01";
        }

        static String processNightlyCirculation(
                LoanReceipt[] receipts) {

            int processed = 0;
            int nullSkipped = 0;
            int referenceOnly = 0;
            int regular = 0;

            for (LoanReceipt r : receipts) {

                if (r == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;

                if (r instanceof ReferenceOnlyLoanReceipt)
                    referenceOnly++;
                else
                    regular++;
            }

            return processed + " processed | " +
                   nullSkipped + " null skipped | " +
                   referenceOnly + " reference-only | " +
                   regular + " regular";
        }
    }

    public static void main(String[] args) {

        LoanReceipt l =
            new LoanReceipt(
                "M101",
                new String[]{"B1", "B2"}
            );

        String[] books = l.getBookIds();
        books[0] = "X";

        System.out.println(l.getBookIds()[0]);

        LoanReceipt updated =
            l.withCorrectedBookId(1, "B3");

        System.out.println(l.getBookIds()[1]);
        System.out.println(updated.getBookIds()[1]);

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "M101",
                new String[]{"B1"},
                "Room 10"
            ),

            null,

            new LoanReceipt(
                "M102",
                new String[]{"B2"}
            )
        };

        System.out.println(
            CirculationLedger.processNightlyCirculation(receipts)
        );
    }
}