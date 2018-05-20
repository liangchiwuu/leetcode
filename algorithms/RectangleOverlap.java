package algorithms;

/**
 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner,
 * and (x2, y2) are the coordinates of its top-right corner.
 * 
 * Two rectangles overlap if the area of their intersection is positive. To be clear, two rectangles that only touch at
 * the corner or edges do not overlap.
 * 
 * Given two rectangles, return whether they overlap.
 * 
 * Example 1:
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * 
 * Example 2:
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * 
 * Notes:
 * Both rectangles rec1 and rec2 are lists of 4 integers.
 * All coordinates in rectangles will be between -10^9 and 10^9.
 */
public class RectangleOverlap {

    public static void main(String[] args) {
        int[] rec1 = { 0, 0, 2, 2 };
        int[] rec2 = { 1, 1, 3, 3 };
        boolean isOverlapping = new RectangleOverlap().new Solution().isRectangleOverlap(rec1, rec2);
        System.out.println(isOverlapping);
    }

    /**
     * A solution by calculating the area of overlapping. We can try to obtain the four edges of intersection by:
     * 
     * Upper edge: min(upper edge of rec1, upper edge of rec2)
     * Lower edge: max(lower edge of rec1, lower edge of rec2)
     * Right edge: min(right edge of rec1, right edge of rec2)
     * Left edge: max(left edge of rec1, left edge of rec2)
     * 
     * Then we know if these two rectangles have overlapping -> area of intersection is positive -> both width and
     * height should be positive.
     * 
     * Time complexity: O(1)
     */
    class Solution {
        public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
            if (rec1 == null || rec2 == null || rec1.length != 4 || rec2.length != 4) {
                return false;
            }

            int width = Math.min(rec1[2], rec2[2]) - Math.max(rec1[0], rec2[0]);
            int height = Math.min(rec1[3], rec2[3]) - Math.max(rec1[1], rec2[1]);

            return width > 0 && height > 0;
        }
    }

}
