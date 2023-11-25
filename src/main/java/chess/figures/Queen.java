package chess.figures;

public class Queen extends Piece{

    public Queen(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "Q" : "q";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
