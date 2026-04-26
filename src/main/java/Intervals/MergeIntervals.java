package Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{3,5}, {1, 4}, {7, 9}, {6, 8}};
        System.out.println("Result: " + Arrays.deepToString(mergeIntervals(intervals)));
    }

    private static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        List<int[]> merged = new ArrayList<>();

        for(int[] interval: intervals){
            if(merged.isEmpty() || interval[0] > merged.get(merged.size()-1)[1]){
                merged.add(interval);
            } else {
                merged.get(merged.size()-1)[1] = Math.max(interval[1], merged.get(merged.size()-1)[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
