package week_9.assigment_problems;

public class Q4_WeatherAlert {

    public static int countAlerts(
            int[] readings, int k, int threshold) {

        int sum = 0;
        int count = 0;

        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        if (sum >= k * threshold) {
            count++;
        }

        for (int i = k; i < readings.length; i++) {

            sum = sum - readings[i - k] + readings[i];

            if (sum >= k * threshold) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};

        System.out.println(
            countAlerts(readings, 3, 4)
        );
    }
}