package AlgorithmsImplementation.hillclimbing;//Program to implement Hill Climbing with random restart to solve N-queens problem
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HillClimbingRandomRestart {
    private static int n = 8 ;
    private static int stepsClimbedAfterLastRestart = 0;
    private static int stepsClimbed =0;
    private static int heuristic = 0;
    private static int randomRestarts = 0;

    //Method to create a new random board
    public static List<Queen> generateBoard() {
        List<Queen> newBoard = new ArrayList<>();

        Random rndm = new Random();
        for(int i=0; i<n; i++){
            newBoard.add(new Queen(rndm.nextInt(n), i));
        }
        printState(newBoard);
        return newBoard;
    }
    //Method to print the Current State
    public static void printState (List<Queen> state) {
        //Creating temporary board from the present board
        int[][] tempBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            //Get the positions of Queen from the Present board and set those positions as 1 in temp board
            tempBoard[state.get(i).getRow()][state.get(i).getColumn()]=1;
        }
        System.out.println();
        for (int i=0; i < n; i++) {
            for (int j= 0; j < n; j++) {
                if(tempBoard[i][j]==1){
                    System.out.print("Q ");
                }
                else{
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    // Method to find Heuristics of a state

    // Method to get the next board with lower heuristic
    /*public static Queen[] nextBoard (Queen[] presentBoard) {
        Queen[] nextBoard = new Queen[n];
        Queen[] tmpBoard = new Queen[n];
        int presentHeuristic = findHeuristic(presentBoard);
        int bestHeuristic = presentHeuristic;
        int tempH;

        for (int i=0; i<n; i++) {
            //  Copy present board as best board and temp board
            nextBoard[i] = new Queen(presentBoard[i].getRow(), presentBoard[i].getColumn());
            tmpBoard[i] = nextBoard[i];
        }
        //  Iterate each column
        for (int i=0; i<n; i++) {
            if (i>0)
                tmpBoard[i-1] = new Queen(presentBoard[i-1].getRow(), presentBoard[i-1].getColumn());
            tmpBoard[i] = new Queen(0, tmpBoard[i].getColumn());
            //  Iterate each row
            for (int j=0; j<n; j++) {
                //Get the heuristic
                tempH = findHeuristic(tmpBoard);
                //Check if temp board better than best board
                if (tempH < bestHeuristic) {
                    bestHeuristic = tempH;
                    //  Copy the temp board as best board
                    for (int k=0; k<n; k++) {
                        nextBoard[k] = new Queen(tmpBoard[k].getRow(), tmpBoard[k].getColumn());
                    }
                }
                //Move the queen
                if (tmpBoard[i].getRow()!=n-1)
                    tmpBoard[i].move();
            }
        }
        //Check whether the present bord and the best board found have same heuristic
        //Then randomly generate new board and assign it to best board
        printState(presentBoard);
        printState(nextBoard);

        if (bestHeuristic == presentHeuristic) {
            randomRestarts++;
            stepsClimbedAfterLastRestart = 0;
            nextBoard = generateBoard();
            heuristic = findHeuristic(nextBoard);
        } else
            heuristic = bestHeuristic;
        stepsClimbed++;
        stepsClimbedAfterLastRestart++;
        return nextBoard;
    }*/
    private int totalCost;
    private double totalRuntime;
    int[] solution;
    int numOfExpandedNodes;

    public double getTotalRuntime() {
        return this.totalRuntime;
    }
    public int[] getSolution() {
        return this.solution;
    }
    public int getTotalCost() {
        return this.totalCost;
    }
    public int getNumOfExpandedNodes() {
        return this.numOfExpandedNodes;
    }

    public void solve(int[] positions) {
        int presentHeuristic;
        /*
        Queen[] presentBoard = generateBoard();
        presentHeuristic = findHeuristic(presentBoard);
        // test if the present board is the solution board
        while (presentHeuristic != 0) {
            //  Get the next board
            // printState(presentBoard);
            presentBoard = nextBoard(presentBoard);
            presentHeuristic  = heuristic;
        }
        //Printing the solution
        printState(presentBoard);

         */
        List<Queen> queens = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
                queens.add(new Queen(positions[i],i));
        }
        Board newBoard = new Board(queens);
        printState(newBoard.getBoardState());
        System.out.println("Starting Hill Climb");
        double startTime = System.currentTimeMillis();
        double finalRunStart = 0.0;
        int expanded = 0, lastExpanded=0;
        while(newBoard.getBoardHeuristic() > 0) {
            PriorityQueue<Board> queue = newBoard.generatePossibleBoards();
            expanded += queue.size();
            lastExpanded += queue.size();
            Board bestNextStep = queue.poll();
            if (bestNextStep.getBoardHeuristic() < newBoard.getBoardHeuristic()) {
                newBoard = bestNextStep;
            }
            else{
                newBoard = new Board(generateBoard());
                randomRestarts++;
                System.out.println("\nTotal number of Steps Climbed in this run: " + stepsClimbed);
                System.out.println("\nStuck in a local Minimum \nStarting a new Run: ");
                stepsClimbed = 0;
                lastExpanded = 0;
                finalRunStart = System.currentTimeMillis();
            }
            stepsClimbed++;
            printState(newBoard.getBoardState());
        }
        printState(newBoard.getBoardState());

        double endTime = System.currentTimeMillis();
        System.out.println("\nTotal number of Steps Climbed: " + stepsClimbed);
        System.out.println("Number of random restarts: " + randomRestarts);
        System.out.println("Total Running Time: "+ (endTime-startTime));
        System.out.println("Last Run Running Time: "+ (endTime-finalRunStart));
        System.out.println("Total Number of Expanded Nodes: "+ expanded);
        System.out.println("Last Run Number of Expanded Nodes: "+ (lastExpanded));

        this.totalCost = stepsClimbed;
        this.numOfExpandedNodes = expanded;
        this.totalRuntime = endTime - startTime;
        this.solution = generateAnswer(newBoard.getBoardState());
    }

    private int[] generateAnswer(List<Queen> boardState) {
        int[] result = new int[8];
        for (int i = 0; i < boardState.size(); i++) {
            result[boardState.get(i).getColumn()] = boardState.get(i).getRow();
        }
        return result;
    }
}