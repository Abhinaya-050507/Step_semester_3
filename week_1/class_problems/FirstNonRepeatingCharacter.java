import java.util.*;

public class FirstNonRepeatingCharacter {

    static char findFirstNonRepeatingChar(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int count = 0;

            for (int j = 0; j < text.length(); j++) {
                if (text.charAt(j) == c)
                    count++;
            }

            if (count == 1)
                return c;
        }

        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: '" + result + "'");
    }
}