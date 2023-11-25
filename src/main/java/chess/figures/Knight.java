package chess.figures;

public class Knight extends Piece{

    public Knight(String color, int x, int y) {
        super(color, x, y);
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
