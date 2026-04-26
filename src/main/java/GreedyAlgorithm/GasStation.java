package GreedyAlgorithm;

public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0, currentGas = 0, startStation = 0;

        for (int i = 0; i < n ; i++){
            int netGain = gas[i] - cost[i];
            totalGas += netGain;
            currentGas += netGain;

            if(currentGas < 0){
                startStation = i + 1;
                currentGas =0;
            }
        }
        return totalGas >= 0 ? startStation : -1;
    }
}
