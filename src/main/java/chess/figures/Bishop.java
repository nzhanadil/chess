package chess.figures;

public class Bishop extends Piece{

    public Bishop(String color, int row, int col) {
        super(color, row, col);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "B" : "b";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        return (Math.abs(toCol-getCol()) == Math.abs(toRow-getRow()));
    }
}
