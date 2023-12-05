package chess.figures;

import chess.Moves;

public class Knight extends Piece {

    private final int[][] directions = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public Knight(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 1);
    }
}