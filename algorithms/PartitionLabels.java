package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 * 
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * 
 * Note:
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> res = new PartitionLabels().new Solution().partitionLabels(s);
        System.out.println(res);
    }

    /**
     * A greedy solution. Since the goal is to cut the string into as many as possible pieces, we can repeatedly select
     * the smallest partition from left to right. To determine if it is valid to cut at index i, we need to make sure
     * that all the characters appear on the left-hand side does not appear on the right-hand side. In order to do this,
     * we need a last[] array to record the last occurrence of each character.
     * 
     * Time complexity: O(n)
     */
    class Solution {
        public List<Integer> partitionLabels(String s) {
            if (s == null || s.length() == 0) {
                return new ArrayList<>();
            }

            int[] last = new int[26];
            for (int i = 0; i < s.length(); i++) {
                last[s.charAt(i) - 'a'] = i;
            }

            List<Integer> ans = new ArrayList<>();

            int j = 0;
            int anchor = 0;
            for (int i = 0; i < s.length(); i++) {
                j = Math.max(last[s.charAt(i) - 'a'], j);
                if (i == j) {
                    // safe to cut
                    ans.add(i - anchor + 1);
                    anchor = i + 1;
                }
            }

            return ans;
        }
    }
}
