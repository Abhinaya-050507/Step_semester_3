package week_2.assignment_problems;

public class LibraryISBNNormalizerValidator {
   static String normalizeCode(String raw) {
        raw = raw.trim();
        return raw.substring(0, 3).toUpperCase() + raw.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13)
            return "Invalid: wrong length";

        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(code.charAt(i)))
                return "Invalid: publisher code must be 3 letters";

        for (int i = 3; i < 13; i++)
            if (!Character.isDigit(code.charAt(i)))
                return "Invalid: body must contain digits";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(code.substring(0, 3));
        sb.append("] YEAR: ");
        sb.append(code.substring(3, 7));
        sb.append(" | CATALOG: ");
        sb.append(code.substring(7));

        return sb.toString();
    }

    public static void main(String[] args) {
        String code = normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(code));
    }
}