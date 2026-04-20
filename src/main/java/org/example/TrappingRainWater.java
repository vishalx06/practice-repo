package org.example;

public class TrappingRainWater {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 2, 2, 5, 1, 0, 2};
        int sol = trappedRainWaterCheck(arr);
            System.out.print(sol);
    }

    private static int trappedRainWaterCheck(int[] arr) {
        int res =0;
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        leftMax[0] = arr[0];
        for(int i = 1 ; i<arr.length ; i++){
            leftMax[i] = Math.max(leftMax[i-1], arr[i]);
        }
        rightMax[arr.length-1] = arr[arr.length-1];
        for(int j = arr.length- 2; j >=0 ; j--){
            rightMax[j] = Math.max(rightMax[j+1], arr[j]);
        }
        for(int k =0; k<arr.length; k++){
            res += (Math.min(leftMax[k], rightMax[k]) - arr[k]);
        }
        return res;
    }
}
