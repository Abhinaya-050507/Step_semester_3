package week_1.assigment_problems;
import java.util.*;

public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {
        String[] words = review.split(" ");

        int shortWords = 0, medium = 0, longWords = 0;

        for (String word : words) {
            int length = word.length();

            if (length <= 4)
                shortWords++;
            else if (length <= 8)
                medium++;
            else
                longWords++;
        }

        System.out.println("Short: " + shortWords);
        System.out.println("Medium: " + medium);
        System.out.println("Long: " + longWords);
    }

    public static void main(String[] args) {
        classifyWordLengths(
            "This movie was absolutely fantastic and thrilling"
        );
    }
}