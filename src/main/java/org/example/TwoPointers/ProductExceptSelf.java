package org.example.TwoPointers;

import java.util.Arrays;

public class ProductExceptSelf {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(printExceptSelf(new int[]{2, 3, 4, 5})));

    }

    private static int[] printExceptSelf(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        int allProd = 1;
        for(int i =0 ; i< n ; i++){
            allProd *= arr[i];
        }
        for(int i =0; i<n ; i++){
            result[i] = allProd/arr[i];
        }
       // System.out.println("all prod = "+ allProd);

        return result;
    }
}
