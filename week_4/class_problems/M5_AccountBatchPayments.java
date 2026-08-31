package week_4.class_problems;
class FeeAccount {
    void processPayment(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    void processPayment(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class M5_AccountBatchPayments {

    static void processPayment(FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount)
            ((HostelFeeAccount) account).processPayment(amount);
        else
            account.processPayment(amount);
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        int hostel = 0;
        int dayScholar = 0;

        for (FeeAccount account : accounts) {

            processPayment(account, 60000);

            if (account instanceof HostelFeeAccount)
                hostel++;
            else
                dayScholar++;
        }

        System.out.println("Hostel accounts processed: " + hostel
                + " | Day-scholar accounts processed: " + dayScholar);
    }
}