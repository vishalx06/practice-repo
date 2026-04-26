package Intervals;

import java.util.Arrays;

// intervals = [(1,5),(3,9),(6,8)]
// false
// intervals = [(10,12),(6,9),(13,15)]
// true
public class CanAttendMeetings {
    public boolean canAttendMeetings(int[][] intervals){
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0], b[0]));
        for(int i  = 1 ; i< intervals.length; i++){
            if(intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CanAttendMeetings solution = new CanAttendMeetings();
        int[][] intervals1 = {{1, 5}, {3, 9}, {6, 8}};
        int[][] intervals2 = {{10, 12}, {6, 9}, {13, 15}};
        boolean result1 = solution.canAttendMeetings(intervals2);
        System.out.println("Can attend all meetings: " + result1 + "\n");
    }
}
