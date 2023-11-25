package chess.figures;

public class Bishop extends Piece{

    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "B" : "b";
    }

    @Override
    public boolean isValidMove() {
        return false;
    }
}
