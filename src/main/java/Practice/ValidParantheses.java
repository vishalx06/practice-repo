package Practice;

import java.util.Stack;

public class ValidParantheses {
    public static void main(String[] args) {
        String str = "{{}}]";
        System.out.println("the parameters are balanced: " + checkBalancedParams(str));
    }

    private static Boolean checkBalancedParams(String str) {
        Stack<Character> set = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                set.push(ch);
            } else {
                if (set.isEmpty()) return false;
                char top = set.pop();
                if ((ch == ')' && top != '(') || (ch == '}' && top != '{') || (ch == ']' && top != '[')) {
                    return false;
                }

            }
        }
        return set.isEmpty();
    }
}
