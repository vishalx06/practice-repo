package Intervals;

import java.util.Arrays;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        int result = nonOverlappingIntervals(intervals);
        System.out.println("Result: " + result);
    }

    private static int nonOverlappingIntervals(int[][] intervals) {
        // Greedy algorithm: sort by end time, keep non-overlapping intervals
        if (intervals.length == 0) {
            return 0;
        }
        // Sort intervals by their end time (greedy choice)
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int end = intervals[0][1];    // End time of last kept interval
        int count = 1;                // Count of non-overlapping intervals kept

        // Process remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            // Non-overlapping interval found: start >= previous end
            if (intervals[i][0] >= end) {
                end = intervals[i][1];    // Update end time
                count++;                  // Keep this interval
            }
            // Overlapping interval: skip it (greedy removal)
        }
        return intervals.length - count; // Number of intervals removed
    }
}
