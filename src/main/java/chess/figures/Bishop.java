package chess.figures;

import chess.Moves;

public class Bishop extends Piece implements Moves {

    private final int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "B" : "b";
    }

    @Override
    public void setAllAvailableMoves() {
        setAllAvailableMovesForLongMovingPieces(directions, getRow(), getCol());
    }
}
