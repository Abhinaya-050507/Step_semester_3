package week_5.class_problems;

public class MovieTicket {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                   ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return accessorContext.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        return "ALLOWED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {
            if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));
    }
}

class Ticket {
    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}