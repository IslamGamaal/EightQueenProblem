package AlgorithmsImplementation.hillclimbing;

import java.util.*;

public class Board {

    private HashSet<String> uniqueBoards;
    private final int boarder = 8;
    private List<Queen> boardState;
    private int boardHeuristic;

    public Board(List<Queen> boardState) {
        this.boardState = boardState;
        boardHeuristic = 0;
        uniqueBoards = new HashSet<>();
        calculateBoardHeuristic();
    }

    public List<Queen> getBoardState() {
        return boardState;
    }

    public void setBoardState(List<Queen> boardState) {
        this.boardState = boardState;
    }

    public int getBoardHeuristic() {
        return boardHeuristic;
    }
    private void updateHeuristic(){
        calculateBoardHeuristic();
    }
    private void calculateBoardHeuristic() {
        boardHeuristic = 0;
        for (int i = 0; i < boardState.size(); i++) {
            for (int j=i+1; j < boardState.size(); j++ ) {
                if (boardState.get(i).ifConflict(boardState.get(j))) {
                    boardHeuristic++;
                }
            }
        }
    }
    public PriorityQueue<Board> generatePossibleBoards(){
        PriorityQueue<Board> possibleBoards = new PriorityQueue<>(1,new Compare());
        Board tempBoard = new Board(duplicate(this.boardState));
        List<Queen> tempBoardStates = tempBoard.getBoardState();
        for (int i = 0; i < tempBoardStates.size(); i++) {
            List<String> possibleMoves = tempBoardStates.get(i).getPossibleMoves(boarder);
            for(String direction : possibleMoves){
                while(tempBoardStates.get(i).getPossibleMoves(boarder).contains(direction)) {
                        tempBoardStates.get(i).move(direction);
                        boolean reserved = false;
                        for (int j = 0; j < tempBoardStates.size(); j++) {
                            if (j != i) {
                                reserved = tempBoardStates.get(i).ifReserved(tempBoardStates.get(j));
                                if(reserved)break;
                            }
                        }
                    if(!reserved) {
                        tempBoard.updateHeuristic();
                        if(!uniqueBoards.contains(tempBoard.getBoardState().toString())) {
                            possibleBoards.add(tempBoard);
                            uniqueBoards.add(tempBoard.getBoardState().toString());
                        }
                        tempBoard = new Board(duplicate(tempBoard.boardState));
                        tempBoardStates = tempBoard.getBoardState();
                    }
                }
                tempBoard = new Board(duplicate(this.boardState));
                tempBoardStates = tempBoard.getBoardState();
            }
        }
        return possibleBoards;
    }
    private List<Queen> duplicate(List<Queen> current){
        List<Queen> duplicate= new ArrayList<>();
        for(Queen q : current){
            duplicate.add(new Queen(q.getRow(), q.getColumn()));
        }
        return duplicate;
    }
}
class Compare implements Comparator<Board> {
    public int compare(Board board1, Board board2) {
        if (board1.getBoardHeuristic() < board2.getBoardHeuristic())
            return -1;
        else if (board1.getBoardHeuristic() > board2.getBoardHeuristic())
            return 1;
        return 0;
    }
}
