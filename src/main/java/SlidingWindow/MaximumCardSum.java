package SlidingWindow;

public class MaximumCardSum {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 5, 1, 3, 2};
        int windowSize = 3;
        System.out.println(maxScore(arr, windowSize));
    }

    private static int maxScore(int[] cards, int windowSize) {
        int total = 0;
        for (int i = 0; i < cards.length; i++) {
            total += cards[i];
        }

        if (windowSize == cards.length) {
            return total;
        }

        int max = 0, left = 0, state = 0;

        for (int right = 0; right < cards.length; right++) {
            state += cards[right];

            if (right - left + 1 == cards.length - windowSize) {
                max = Math.max(max, total - state);
                state -= cards[left];
                left++;
            }
        }

        return max;
    }
}
