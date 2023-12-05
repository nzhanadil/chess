package chess.figures;

import chess.Moves;

public class Bishop extends Piece {

    private final int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 0);
    }
}