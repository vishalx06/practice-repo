package GreedyAlgorithm;

/*
*   Input:
    prices = [3,4,6,2,5,8]
    Output: 6
    Explanation: Buy on day 4 (price = 2) and sell on day 6 (price = 8), profit = 8-2 = 6.

    Input:
    prices = [9,7,5,3,1]
    Output: 0
* */
public class BestTimeToBusAndSellStocks {
    public static Integer maxProfit(int[] prices){
        if(prices == null || prices.length <= 1){
            return  0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            int currentPrice = prices[i];
            int profit = currentPrice - minPrice;
            maxProfit = Math.max(maxProfit, profit);

            minPrice = Math.min(minPrice,currentPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{9,7,5,3,1};
        System.out.println("Max profit: "+ maxProfit(prices));
    }
}
