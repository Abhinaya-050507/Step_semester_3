package week_5.class_problems;

public class SubTicket {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private"))
            return accessorContext.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("default"))
            return accessorContext.equals("SAME_CLASS") ||
                   accessorContext.equals("SAME_PACKAGE")
                   ? "ALLOWED" : "DENIED";

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"))
                return "DENIED";

            return accessorContext.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";
        }

        return "ALLOWED";
    }

    public static void main(String[] args) {

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
    }
}