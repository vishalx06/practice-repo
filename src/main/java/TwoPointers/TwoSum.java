package TwoPointers;

public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 6, 8, 10, 13};
        int target = 13;
        System.out.println("contains two sum: " + checkTwoSum(arr, target));
    }

    private static Boolean checkTwoSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
//        Arrays.sort(arr);
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == target) {
                return true;
            }
            if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
