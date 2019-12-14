package AlgorithmsImplementation.csp;

import java.util.ArrayList;

public class CSP {
    final int N = 8;

    private double totalTime;
    private int[] solution;
    private int cost;

    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                        + " ");
            System.out.println();
        }
    }

    boolean isSafe(int board[][], int row, int col)
    {
        int i, j;

        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    boolean solveNQUtil(int board[][], int col)
    {
        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                cost++;
                if (solveNQUtil(board, col + 1) == true)
                    return true;
                board[i][col] = 0;
            }
        }
        return false;
    }

    public boolean solveNQ(int[] positions)
    {
        double startTime = System.currentTimeMillis();
        int[] solution = new int[8];
        int board[][] = new int[8][8];
        for(int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = 0;
        for(int i = 0 ; i < 1; i++) {
            board[positions[i]][i] = 1;
        }
        boolean hasSolution = solveNQUtil(board, 1);
        double endTime = System.currentTimeMillis();
        double totalTime = endTime - startTime;
        if (!hasSolution) {
            System.out.print("Solution does not exist");
            return false;
        } else {
            for (int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(board[i][j] == 1) {
                        solution[j] = i;
                    }
                }
            }
            this.solution = solution;
            this.totalTime = totalTime;
        }

        printSolution(board);
        return true;
    }
    public double getTotalTime() {
        return this.totalTime;
    }
    public int getCost() {
        return this.cost;
    }
    public int[] getSolution() {
        return this.solution;
    }
}
