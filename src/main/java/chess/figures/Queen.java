package chess.figures;

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
        return false;
    }
}
