package TwoPointers;

public class LongestPalindrome {
    public static void main(String[] args) {
        String str = "babad";
        System.out.println(getLongestPalindrome(str));
    }

    private static String getLongestPalindrome(String str) {
        String res = "";
        if( str == null || str.length()<1) return "";

        int start =0, end =0;
        for(int i=0; i < str.length(); i++){
            int len1 = expandStr(str, i, i);
            int len2 = expandStr(str,i, i+1);
            int len = Math.max(len1, len2);

            if(len > end - start){
                start = i - (len -1) /2;
                end = i + len/2;
            }
        }
        return  str.substring(start,end+1);
    }

    private static int expandStr(String str, int left, int right) {
        while ( left >=0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return right - left -1;
    }
}
