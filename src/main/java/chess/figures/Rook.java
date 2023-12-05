package chess.figures;

import chess.Moves;

public class Rook extends Piece {

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 0);
    }
}