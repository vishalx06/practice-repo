package org.example;

import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithoutRepeating {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    private static int lengthOfLongestSubstring(String str) {
        int n = str.length();
        int max = Integer.MIN_VALUE;
        int left = 0;
        Set<Character> set = new HashSet<>();

        for( int right =0; right< n; right++){
            if(set.contains(str.charAt(right))){
                set.remove(str.charAt(left));
                left++;
            }
            set.add(str.charAt(right));
            max = Math.max(max, right - left + 1);
        }

        return  max;
    }
}
