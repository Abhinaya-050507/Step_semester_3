package week_5.assignment_problems;

public class Desk {

    static String classifyAccess(String modifier, String context) {

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return context.equals("SAME_CLASS") ||
                   context.equals("SAME_PACKAGE")
                   ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected")) {

            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            if (context.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"))
                return "DENIED";

            return context.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        return "ALLOWED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            if (classifyAccess(attempts[i][0], attempts[i][1])
                    .equals("DENIED")) {

                return attempts[i][0] +
                       " via " +
                       attempts[i][1] +
                       " (attempt #" +
                       (i + 1) + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"private", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"}
        };

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            firstDeniedAttempt(attempts)
        );
    }
}