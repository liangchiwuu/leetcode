package algorithms;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area
 * of largest rectangle in the histogram.
 * 
 *        6
 *      5┌─┐
 *     ┌─┤ │
 *     │ │ │  3
 *  2  │ │ │2┌─┐
 * ┌─┐1│ │ ├─┤ │
 * │ ├─┤ │ │ │ │
 * │ │ │ │ │ │ │
 * └─┴─┴─┴─┴─┴─┘
 * 
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * 
 *        6
 *      5┌─┐
 *     ┌─┤ │
 *     │*│*│  3
 *  2  │*│*│2┌─┐
 * ┌─┐1│*│*├─┤ │
 * │ ├─┤*│*│ │ │
 * │ │ │*│*│ │ │
 * └─┴─┴─┴─┴─┴─┘
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = new int[] { 2, 1, 5, 6, 2, 3 };
        int maxArea = new LargestRectangleInHistogram().new Solution().largestRectangleArea(heights);
        System.out.println(maxArea);
    }

    /**
     * A brute-force solution. Simply loop through each height and find the rectangle that is 'limiting' by this height.
     * 
     * Time complexity: O(n^2)
     */
    class Solution {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            int maxArea = Integer.MIN_VALUE;
            for (int i = 0; i < heights.length; i++) {
                int height = heights[i];

                int leftBound = i;
                while (leftBound > 0 && heights[leftBound - 1] >= heights[i]) {
                    leftBound--;
                }

                int rightBound = i;
                while (rightBound < heights.length - 1 && heights[rightBound + 1] >= heights[i]) {
                    rightBound++;
                }

                maxArea = Math.max(maxArea, height * (rightBound - leftBound + 1));
            }

            return maxArea;
        }
    }

    /**
     * !!! RECOMMEND TO DRAW IT OUT !!!
     * 
     * A solution with stack. The idea is to keep pushing the 'index' of the height into the stack and maintain the
     * 'heights' in the stack to always in ascending order. This way when an 'index' is popped out from the stack, we
     * can easily calculate the left/right boundary for the rectangle. Let's try with [2, 1, 5, 6, 2, 3]:
     * 
     *        6
     *      5┌─┐
     *     ┌─┤ │
     *     │ │ │  3
     *  2  │ │ │2┌─┐
     * ┌─┐1│ │ ├─┤ │
     * │ ├─┤ │ │ │ │
     * │ │ │ │ │ │ │
     * └─┴─┴─┴─┴─┴─┘
     *  0 1 2 3 4 5
     * 
     * The first height is 2 at index 0, since the stack is empty, this height is pushed to the stack.
     * stack -> 0(2)
     * 
     * Next, we have height 1 at index 1, now the top of the stack has a larger height than current, we pop it out.
     * stack -> empty
     * It is at this moment we can calculate the largest rectangle with a max height of 2 at index 0. Since the stack is
     * now empty, the left bound is 0, and the right bound is i - 1 = 0 (since i is the first index that has a smaller
     * height than stack.peek()). That gives a rectangle of size 2. Then, we can push the current height into the stack.
     * stack -> 1(1)
     * 
     * Next, since height 5 is larger than height[stack.peek()], we push index 2 into the stack.
     * stack -> 1(1) 2(5)
     * 
     * Next, index 3 is pushed to the stack. Note how the 'heights' in stack is always in ascending order.
     * stack -> 1(1) 2(5) 3(6)
     * 
     * Next, we have height 2 at index 4. Since the height[stack.peek()] is larger than 2, we will pop 3(6) out.
     * stack -> 1(1) 2(5)
     * Now, what's the biggest rectangle that has a height 6 at index 3? The left bound should be stack.peek() + 1 since
     * 2(5) is the last index that has a smaller height than 3(6), and the right bound should be i - 1. This yields to:
     * width = rightBound - leftBound + 1 = (i - 1) - (stack.peek() + 1) + 1 = i - 1 - stack.peek()
     * So the max area can now be calculated as : 6 * (4 - 1 - 2) = 6. Since the top of the stack is still larger than
     * current height, we will pop again.
     * stack -> 1(1)
     * With the same method, the max area for the rectangle (with a max height of 5 at index 2) is 5 * (4 - 1 - 1) = 10.
     * Then finally we can push the current height into the stack.
     * stack -> 1(1) 4(2)
     * 
     * Next, height 3 at index 5 is pushed into the stack.
     * stack -> 1(1) 4(2) 5(3)
     * 
     * Now we have reached the end of the height, we can assume that the end is a height of 0, so we will have to pop
     * everything out. First, 5(3) is popped out, max area = 3 * (6 - 1 - 4) = 3 * 1 = 3.
     * stack -> 1(1) 4(2)
     * Next, 4(2) is popped out, max area = 2 * (6 - 1 - 1) = 2 * 4 = 8.
     * stack -> 1(1)
     * Finally, 1(1) is popped out, stack is empty, max area = 1 * 6
     * stack -> empty
     * 
     * Since we've scan through all the heights and the stack is empty, we can return the possible max area = 10.
     * 
     * Time complexity: O(n)
     */
    class Solution2 {
        public int largestRectangleArea(int[] heights) {
            if (heights == null || heights.length == 0) {
                return 0;
            }

            int max = Integer.MIN_VALUE;
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i <= heights.length; i++) {
                int current = i == heights.length ? 0 : heights[i];

                while (!stack.isEmpty() && current <= heights[stack.peek()]) {
                    int h = heights[stack.pop()];
                    int w = stack.isEmpty() ? i : (i - 1) - stack.peek();
                    max = Math.max(max, h * w);
                }

                stack.push(i);
            }

            return max;
        }
    }

}
