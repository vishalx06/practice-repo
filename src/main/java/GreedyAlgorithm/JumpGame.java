package GreedyAlgorithm;

/**
 * Input:
 *
 * nums = [1, 3, 0, 1, 4]
 * Output:
 *
 * true
 * Explanation: You can jump from index 0 to index 1, then jump from index 1 to index 4 which is the last index.
 *
 * Example: Input:
 *
 * nums = [2,2,1,0,5,1,1]
 * Output:
 *
 * false
 *
 * **/
public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println("possible: "+ canjump(nums));
        System.out.println("smallest path for jump: "+jump(nums));
    }

    private static int jump(int[] nums) {
        if(nums.length <= 1 ) return 0;
        int jump = 0, currentEnd = 0, farthest = 0, n = nums.length;
        for (int i = 0; i<n ; i++){
            farthest = Math.max(farthest, i+nums[i]);
            if(i == currentEnd){
                jump++;
                currentEnd = farthest;
            }
        }
        return jump;
    }

    private static Boolean canjump(int[] nums) {
        int furthest = 0;
        int length = nums.length-1;
        for(int i = 0; i <= length; i++){
            if(i > furthest){
                return false;
            }
            furthest = Math.max(furthest, i + nums[i]);
            if( furthest >= length){
                return true;
            }
        }
        return furthest >= length;
    }
}
