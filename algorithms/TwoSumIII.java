package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * 
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 * 
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */
public class TwoSumIII {

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSumIII().new TwoSum();
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);
        boolean result = twoSum.find(4);
        System.out.println(result);
    }

    /**
     * Similar to Two Sum, the key here is to handle when a number appears multiple times.
     * 
     * Time complexity: O(n) for find()
     */
    public class TwoSum {
        private Map<Integer, Integer> nums = new HashMap<>();

        public void add(int number) {
            if (nums.containsKey(number)) {
                nums.put(number, nums.get(number) + 1);
            } else {
                nums.put(number, 1);
            }
        }

        public boolean find(int value) {
            for (int num : nums.keySet()) {
                if (num == value - num && nums.get(num) > 1 ||
                        num != value - num && nums.containsKey(value - num)) {
                    return true;
                }
            }
            return false;
        }
    }

}
