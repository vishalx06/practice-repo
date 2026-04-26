package Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*  Input:
    intervals = [[1,3],[6,9]]
    newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Explanation: The new interval [2,5] overlaps with [1,3], so they are merged into [1,5].
*/
public class InsertIntervals {
    public static int[][] insertIntervals(int[][] intervals, int[] newInterval) {
        // Interval insertion with merging: three-phase approach
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals that end before newInterval starts (no overlap)
        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge all overlapping intervals with newInterval
        while( i<n && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        // Add the merged newInterval
        merged.add(newInterval);

        // Phase 3: Add all remaining intervals (after newInterval)
        for(int j=i; j<n; j++){
            merged.add(intervals[j]);
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newIntervals = {2, 5};
        int[][] result = insertIntervals(intervals1, newIntervals);
        System.out.println("Result: " + Arrays.deepToString(result));
    }
}
