package chess.figures;

public class Rook extends Piece{

    public Rook(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return false;
    }
}
