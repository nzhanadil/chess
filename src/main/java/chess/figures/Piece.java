package chess.figures;

abstract public class Piece {
    String color;
    int row, col;

    public Piece(String color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public String getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setLocation(int row, int col){
        this.row = row;
        this.col = col;
    }

    public abstract String getSymbol();

    public abstract boolean isValidMove(int toRow, int toCol);
}
