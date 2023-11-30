package chess.figures;

import java.util.List;

public class Queen extends Piece{

    public Queen(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "Q" : "q";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return (Math.abs(toRow-getRow()) == Math.abs(toCol-getCol()) || (toCol == getCol() || toRow == getRow()));
    }

    @Override
    public void setAllAvailableMoves() {

    }
}
