package algorithms;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of
 * your product fails the quality check. Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following
 * ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 */
public class FirstBadVersion {

    static class VersionControl {
        static boolean[] versionStatus = new boolean[] { false, false, false, true, true };

        public static boolean isBadVersion(int version) {
            return versionStatus[version - 1];
        }
    }

    public static void main(String[] args) {
        int result = new FirstBadVersion().new Solution().firstBadVersion(5);
        System.out.println(result);
    }

    /**
     * A standard binary search.
     * 
     * Time complexity: O(log n)
     * Space complexity: O(1)
     */
    class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int start = 1;
            int end = n;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (isBadVersion(start)) {
                return start;
            }
            return end;
        }
    }

}
