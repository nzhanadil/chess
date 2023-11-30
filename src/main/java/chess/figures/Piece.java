package chess.figures;

import chess.Board;

import java.util.ArrayList;
import java.util.List;

abstract public class Piece extends Board {
    String color;
    int row, col;
    List<int[]> allAvailableMoves;

    public Piece(String color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public String getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setLocation(int row, int col){
        this.row = row;
        this.col = col;
    }

    public List<int[]> getAllAvailableMoves(){
        return allAvailableMoves;
    }

    public abstract String getSymbol();

    public boolean isValidMove(int toRow, int toCol){
        for(int[] arr : getAllAvailableMoves()){
            if(arr[0] == toRow && arr[1] == toCol){
                return true;
            }
        }
        return false;
    }

    public abstract void setAllAvailableMoves();

    //TODO - method for checking if there is no elements between A and B
}
