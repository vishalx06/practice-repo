package org.example.TwoPointers;

import java.util.Arrays;

public class TriangleNumbers {
    public static void main(String[] args) {
        int[] arr = new int[]{11, 4, 9, 6, 15, 18};
        System.out.println("No of triangle possible: " + trianglePossible(arr));
    }

    private static int trianglePossible(int[] arr) {
        int count = 0;
        Arrays.sort(arr);

        for (int i = arr.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (arr[left] + arr[right] > arr[i]) {
                    count += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }
}
