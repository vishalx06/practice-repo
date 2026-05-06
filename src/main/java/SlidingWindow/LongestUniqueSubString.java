package SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestUniqueSubString {
    public static void main(String[] args) {
        String s = "geeksforgeeks";
        System.out.println(longestUniqueSubstr(s));
    }

    public static int longestUniqueSubstr(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, max = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);

            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            map.put(ch, end);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
