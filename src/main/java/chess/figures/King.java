package chess.figures;

public class King extends Piece{

    public King(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "K" : "k";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
