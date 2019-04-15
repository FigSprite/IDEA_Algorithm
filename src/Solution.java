import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static int getMaxAreaByBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                int curHeight = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = (i - leftLessIndex - 1) * height[curHeight];
                maxArea = Math.max(area, maxArea);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int area = (height.length - leftLessIndex - 1) * height[curHeight];
            maxArea = Math.max(area, maxArea);
        }
        System.out.println(maxArea);
        return maxArea;
    }

    public static int getMaxArea(int[][] maps) {
        if (maps == null || maps.length == 0 || maps[0].length == 0) {
            return 0;
        }
        int height[] = new int[maps[0].length];
        int maxArea = 0;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length; j++) {
                height[j] = maps[i][j] == 0 ? 0 : height[j] + 1;

            }
            int area = getMaxAreaByBottom(height);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0},};
        System.out.println(getMaxArea(map));
    }
}
