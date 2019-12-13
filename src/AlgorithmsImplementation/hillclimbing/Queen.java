package AlgorithmsImplementation.hillclimbing;

import java.util.ArrayList;
import java.util.List;

public class Queen {
    private int row;
    private int column;

    public Queen(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move(String direction) {
        switch (direction) {
            case Moves.UP:
                row--;
                break;
            case Moves.UP_RIGHT:
                row--;
                column++;
                break;
            case Moves.RIGHT:
                column++;
                break;
            case Moves.DOWN_RIGHT:
                row++;
                column++;
                break;
            case Moves.DOWN:
                row++;
                break;
            case Moves.DOWN_LEFT:
                row++;
                column--;
                break;
            case Moves.LEFT:
                column--;
                break;
            case Moves.UP_LEFT:
                row--;
                column--;
                break;
        }
    }

    public boolean ifConflict(Queen q){
        //  Check rows and columns
        if(row == q.getRow() || column == q.getColumn())
            return true;
            //  Check diagonals
        else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
            return true;
        return false;
    }
    public boolean ifReserved(Queen q){
        if(row == q.getRow() && column == q.getColumn())
            return true;
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public List<String> getPossibleMoves(int boarder){
        List<String> possibleMoves = new ArrayList<>();
        if(this.row == 0 && this.column == 0){
            possibleMoves.add(Moves.RIGHT);
            possibleMoves.add(Moves.DOWN_RIGHT);
            possibleMoves.add(Moves.DOWN);
        }
        else if(this.row == 0 && this.column == boarder-1){
            possibleMoves.add(Moves.DOWN);
            possibleMoves.add(Moves.DOWN_LEFT);
            possibleMoves.add(Moves.LEFT);
        }
        else if(this.row == boarder-1 && this.column == 0){
            possibleMoves.add(Moves.UP);
            possibleMoves.add(Moves.UP_RIGHT);
            possibleMoves.add(Moves.RIGHT);
        }
        else if(this.row == boarder-1 && this.column == boarder-1){
            possibleMoves.add(Moves.LEFT);
            possibleMoves.add(Moves.UP_LEFT);
            possibleMoves.add(Moves.UP);
        }
        else if(this.row == 0){
            possibleMoves.add(Moves.RIGHT);
            possibleMoves.add(Moves.DOWN_RIGHT);
            possibleMoves.add(Moves.DOWN);
            possibleMoves.add(Moves.DOWN_LEFT);
            possibleMoves.add(Moves.LEFT);
        }
        else if(this.row == boarder-1){
            possibleMoves.add(Moves.LEFT);
            possibleMoves.add(Moves.UP_LEFT);
            possibleMoves.add(Moves.UP);
            possibleMoves.add(Moves.UP_RIGHT);
            possibleMoves.add(Moves.RIGHT);
        }
        else if(this.column == 0){
            possibleMoves.add(Moves.UP);
            possibleMoves.add(Moves.UP_RIGHT);
            possibleMoves.add(Moves.RIGHT);
            possibleMoves.add(Moves.DOWN_RIGHT);
            possibleMoves.add(Moves.DOWN);
        }
        else if(this.column == boarder-1){
            possibleMoves.add(Moves.DOWN);
            possibleMoves.add(Moves.DOWN_LEFT);
            possibleMoves.add(Moves.LEFT);
            possibleMoves.add(Moves.UP_LEFT);
            possibleMoves.add(Moves.UP);

        }
        else{
            possibleMoves.add(Moves.UP);
            possibleMoves.add(Moves.UP_RIGHT);
            possibleMoves.add(Moves.RIGHT);
            possibleMoves.add(Moves.DOWN_RIGHT);
            possibleMoves.add(Moves.DOWN);
            possibleMoves.add(Moves.DOWN_LEFT);
            possibleMoves.add(Moves.LEFT);
            possibleMoves.add(Moves.UP_LEFT);
        }
        return possibleMoves;
    }
}