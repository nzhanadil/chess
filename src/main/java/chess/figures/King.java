package chess.figures;

import chess.Moves;

public class King extends Piece implements Moves {

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "K" : "k";
    }

    @Override
    public void setAllAvailableMoves() {

    }
}
