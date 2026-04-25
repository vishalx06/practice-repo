package org.example.TwoPointers;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 2, 4, 1, 3, 2};
        System.out.println(containerWithMostWater(arr));
    }

    private static int containerWithMostWater(int[] arr) {
        int max = 0;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int width = right - left;
            int height = Math.min(arr[right], arr[left]);
            int currentArea = width * height;

            max = Math.max(max, currentArea);

            if (arr[right] < arr[left]) {
                right--;
            } else {
                left++;
            }
        }
        return max;
    }
}
