package chess.figures;

public class Knight extends Piece{

    public Knight(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "N" : "n";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
