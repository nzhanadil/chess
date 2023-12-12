package chess.figures;

import chess.Moves;

public class King extends Piece {

    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public void setAllAvailableMoves() {
        Moves.setAllAvailableMoves(board[getRow()][getCol()], directions, 1);
    }
}