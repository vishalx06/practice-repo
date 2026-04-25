package SlidingWindow;

import java.util.HashMap;

public class MaxSum {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 2, 3, 4, 6, 7, 7, -1};
        int windowSize = 4;
        System.out.println("Simple Max sum of an array:" + maxSum(arr, windowSize));
        System.out.println("No Duplicate Max sum of an array:" + maxSumWithoutDuplicates(arr, windowSize));
    }

    private static int maxSum(int[] arr, int k) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int state = 0;
        for (int right = 0; right < arr.length; right++) {
            state += arr[right];
            if (right - left + 1 == k) {
                max = Math.max(state, max);
                state -= arr[left];
                left++;
            }
        }
        return max;
    }

    private static int maxSumWithoutDuplicates(int[] arr, int k) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int state = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int right = 0; right < arr.length; right++) {
            state += arr[right];
            freqMap.put(arr[right], freqMap.getOrDefault(arr[right], 0) + 1);

            if (right - left + 1 == k) {
                if (freqMap.size() == k) {
                    max = Math.max(max, state);
                }
                state -= arr[left];
                freqMap.put(arr[left], freqMap.get(arr[left]) - 1);
                if (freqMap.get(arr[left]) == 0) {
                    freqMap.remove(arr[left]);
                }
                left++;
            }

        }
        return max == Integer.MIN_VALUE ? 0 : max ;
    }
}
