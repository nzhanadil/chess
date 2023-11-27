package chess.figures;

public class King extends Piece{

    public King(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "K" : "k";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return Math.abs(toCol-getCol())<=1 && Math.abs(toRow-getRow())<=1;
    }
}
