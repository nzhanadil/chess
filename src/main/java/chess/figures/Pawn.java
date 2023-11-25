package chess.figures;

public class Pawn extends Piece{

    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
