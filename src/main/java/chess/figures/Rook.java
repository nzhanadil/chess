package chess.figures;

public class Rook extends Piece{

    public Rook(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
