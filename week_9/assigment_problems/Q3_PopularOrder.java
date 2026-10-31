package week_9.assigment_problems;
import java.util.HashMap;
public class Q3_PopularOrder {

    public static Object[] mostPopular(String[] orders) {

        HashMap<String, Integer> map = new HashMap<>();

        String popular = orders[0];
        int maxCount = 0;

        for (String item : orders) {

            int count = map.getOrDefault(item, 0) + 1;
            map.put(item, count);

            if (count > maxCount) {
                maxCount = count;
                popular = item;
            }
        }

        return new Object[]{popular, maxCount};
    }

    public static void main(String[] args) {

        String[] orders = {
            "dosa", "idli", "dosa",
            "vada", "idli", "dosa"
        };

        Object[] result = mostPopular(orders);

        System.out.println(
            "(" + result[0] + ", " + result[1] + ")"
        );
    }
}