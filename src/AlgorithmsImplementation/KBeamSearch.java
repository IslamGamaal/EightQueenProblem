package AlgorithmsImplementation;

import java.util.Arrays;
import java.util.Comparator;

public class KBeamSearch {
    private int numOfExpandedNodes = 0;
    private double totalRunTime = -1;
    private int steps = -1;

    public int[] solve(int n, int[] givenState, int maxNumOfIterations, int k) {
        double startTime = System.currentTimeMillis();
        int[][] states = new int[k][];
        states[0] = givenState;
        for (int i = 1; i < k; i++)
            states[i] = SolverUtils.generateRandomState(n);

        for (int x = 0; x < maxNumOfIterations; x++) {
            int[][] newStates = new int[n * k][]; // each state of the k states is a move for a single queen from the n queens by one step
            for (int i = 0; i < k; i++) {
                int costToBeat = SolverUtils.getHeuristicCost(states[i]);

                if (costToBeat == 0) {
                    double endTime = System.currentTimeMillis();
                    totalRunTime = endTime - startTime;
                    steps = x;
                    return states[i];
                }

                for (int col = 0; col < n; col++) {
                    newStates[i * n + col] = makeMove(states[i], col, costToBeat);

                    if (newStates[i * n + col] == null)
                        newStates[i * n + col] = SolverUtils.generateRandomState(n);
                }
            }
            numOfExpandedNodes += k;
            Arrays.sort(newStates, Comparator.comparingInt(SolverUtils::getHeuristicCost));
            states = Arrays.copyOfRange(newStates, 0, k);
        }
        return null;
    }

    private int[] makeMove(int r[], int col, int costToBeat) {

        int n = r.length;
        for (int row = 0; row < n; row++) {
            // we do not need to evaluate because we already know current cost by costToBeat.
            if (row == r[col])
                continue;

            int tmpRow = r[col];
            r[col] = row;
            int cost = SolverUtils.getHeuristicCost(r);
            if (costToBeat > cost) {
                r[col] = row;
                return r;
            }
            r[col] = tmpRow;
        }
        return null;
    }

    public int getNumOfExpandedNodes() {
        return numOfExpandedNodes;
    }

    public double getTotalRunTime() {
        return totalRunTime;
    }

    public int getSteps() {
        return steps;
    }

}
