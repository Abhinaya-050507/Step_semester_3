package week_9.class_problems;
import java.util.HashSet;
public class Q4_TargetArray {

    public static boolean hasPairWithSum(int[] nums, int target) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            if (set.contains(target - num)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static boolean bruteForce(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};

        System.out.println(hasPairWithSum(nums, 9));
        System.out.println(hasPairWithSum(nums, 20));
    }
}