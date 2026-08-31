package week_2.assignment_problems;

public class WordReversalEncoder {
    static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        String result = "";

        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            result += sb.reverse() + " ";
        }

        return result.trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseEachWord("hello club"));
    }
}