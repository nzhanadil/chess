package chess.figures;

import chess.Moves;

public class Queen extends Piece implements Moves {

    private final int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "Q" : "q";
    }

    @Override
    public void setAllAvailableMoves() {
        setAllAvailableMovesForLongMovingPieces(directions, getRow(), getCol());
    }
}
