package chess.figures;

import chess.Moves;

public class Queen extends Piece {

    private final int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 0);
    }
}