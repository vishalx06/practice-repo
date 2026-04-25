package org.example.TwoPointers;

public class SortColors {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 2, 0, 1, 0, 1, 0, 1};
        int[] sol = move012(arr);
        for (int sols : sol) {
            System.out.print(sols + " ");
        }
    }

    private static int[] move012(int[] arr) {
        int zeroIndex = 0, twoIndex = arr.length - 1, i = 0;
        while (i < twoIndex) {
            if (arr[i] == 0) {
                int temp = arr[zeroIndex];
                arr[zeroIndex] = arr[i];
                arr[i] = temp;
                zeroIndex++;
                i++;
            } else if (arr[i] == 2) {
                int temp = arr[i];
                arr[i] = arr[twoIndex];
                arr[twoIndex] = temp;
                twoIndex--;
            } else {
                i++;
            }
        }
        return arr;
    }
}
