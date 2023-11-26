package chess.figures;

public class Pawn extends Piece{

    public Pawn(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return false;
    }
}
