package org.example;

public class MoveZeros {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,3,0,0};
        int[] sol= moveZerosToEnd(arr);
        for(int sols: sol ){
            System.out.print(sols + " ");
        }
    }

    private static int[] moveZerosToEnd(int[] arr) {
        int left = 0;
        for(int right =0 ; right < arr.length; right ++){
            if(arr[right]!=0){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }
        }
        return arr;
    }
}
