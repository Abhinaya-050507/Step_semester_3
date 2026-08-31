package week_4.class_problems;

class FeeAccount {
    String regNo;
    double totalFees;

    FeeAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    final double calculateLateFee(int daysLate) {
        return totalFees * daysLate / 100.0;
    }

    final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.println(regNo + " - On time, no late fee");
        } else {
            System.out.println(regNo + " | Total Fee: Rs " + totalFees
                    + " | Late Fee: Rs " + calculateLateFee(daysLate));
        }
    }
}

public class M3_LateFeesSkipOnTimeAccounts {
    public static void main(String[] args) {

        FeeAccount[] accounts = {
            new FeeAccount("RA001", 200000),
            new FeeAccount("RA002", 150000),
            new FeeAccount("RA003", 180000),
            new FeeAccount("RA004", 220000)
        };

        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < accounts.length; i++)
            accounts[i].printSummary(daysLate[i]);
    }
}