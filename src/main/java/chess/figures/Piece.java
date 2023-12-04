package chess.figures;

import chess.Board;

import java.util.ArrayList;
import java.util.List;

abstract public class Piece extends Board {

    boolean hasMoved;
    String color;
    int row, col;
    List<int[]> allAvailableMoves;
    List<Piece> allBackedUpPieces;
    ;

    public Piece(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.hasMoved = false;
        this.allAvailableMoves = new ArrayList<>();
        this.allBackedUpPieces = new ArrayList<>();
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

    public void setLocation(int row, int col) {
        this.hasMoved = true;
        this.row = row;
        this.col = col;
    }

    public List<int[]> getAllAvailableMoves() {
        return allAvailableMoves;
    }

    public List<Piece> getAllBackedUpPieces() {
        return allBackedUpPieces;
    }

    public boolean isValidMove(int toRow, int toCol) {
        for (int[] arr : getAllAvailableMoves()) {
            if (arr[0] == toRow && arr[1] == toCol) {
                return true;
            }
        }
        return false;
    }

    public abstract String getSymbol();

    public abstract void setAllAvailableMoves();
}
