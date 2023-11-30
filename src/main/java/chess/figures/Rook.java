package chess.figures;

import chess.Moves;

public class Rook extends Piece implements Moves{

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public void setAllAvailableMoves() {
        allAvailableMoves = longMoves(directions, getRow(), getCol());
    }

}
