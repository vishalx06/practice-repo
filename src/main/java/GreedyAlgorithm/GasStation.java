package GreedyAlgorithm;

public class GasStation {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        // Greedy approach: find starting station that allows complete circuit
        int n = gas.length;
        int totalTank = 0;    // Total gas surplus/deficit across all stations
        int currentTank = 0;  // Current tank level during simulation
        int startStation = 0; // Candidate starting station

        // Process each gas station
        for (int i = 0; i < n; i++) {
            int netGain = gas[i] - cost[i];  // Net gain/loss at station i
            totalTank += netGain;            // Track overall feasibility
            currentTank += netGain;          // Track current journey

            // If we run out of gas, restart from next station
            if (currentTank < 0) {
                startStation = i + 1;  // Next station becomes new start
                currentTank = 0;       // Reset tank for new journey
            }
        }

        // Circuit possible only if total gain is non-negative
        return totalTank >= 0 ? startStation : -1;
    }

    public static void main(String[] args) {
        int gas[] = {5,2,0,3,3};
        int cost[] = {1,2,2,1,1};
        System.out.println("station selected: "+ canCompleteCircuit(gas,cost));
    }
}
