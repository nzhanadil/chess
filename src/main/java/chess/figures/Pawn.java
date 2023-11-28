package chess.figures;

public class Pawn extends Piece{
    boolean hasMoved;

    public Pawn(String color, int row, int col) {
        super(color, row, col);
        this.hasMoved = false;
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }

    @Override
    public boolean isValidMove(int toRow, int toCol) {
        //TODO - need to add logic for black or white moves(Each can move only forwards)
        if(!hasMoved && toCol==getCol() && Math.abs(getRow()-toRow)==2) {
            hasMoved = true;
            return true;
        }
        if(Math.abs(toCol-getCol())<=1 && Math.abs(getRow()-toRow)==1){
            hasMoved = true;
            return true;
        }
        return false;
    }
}
