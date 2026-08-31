package week_2.assignment_problems;

import java.util.HashMap;

public class StopWordFilteredWordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        feedback = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = feedback.split("\\s+");

        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {

            boolean stop = false;

            for (String s : stopWords) {
                if (word.equals(s))
                    stop = true;
            }

            if (!stop) {
                if (map.containsKey(word))
                    map.put(word, map.get(word) + 1);
                else
                    map.put(word, 1);
            }
        }

        for (String word : map.keySet()) {
            System.out.println(word + ": " + map.get(word));
        }
    }

    public static void main(String[] args) {

        printFilteredWordFrequency(
            "The mentor was great, the session was great and clear."
        );
    }
}